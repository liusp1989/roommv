package com.liusp.roommv.index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.springframework.util.StringUtils;

import com.liusp.roommv.common.RoommvConstant;
import com.liusp.roommv.vo.Html;

public class HtmlIndexer implements Indexer {
	public static final Logger logger = Logger.getLogger(HtmlIndexer.class);
	private String indexesPath;
	private String filesPath;
	private static Analyzer analyzer;
	private OpenMode openMode = OpenMode.APPEND;// 索引创建模式 增量还是新增

	public HtmlIndexer(final String indexesPath, final String filesPath,
			OpenMode openMode) {
		this.indexesPath = indexesPath;
		this.filesPath = filesPath;
		this.openMode = openMode;
	}

	public HtmlIndexer() {
		// TODO Auto-generated constructor stub
	}


	public void createSearchIndexes() throws IOException {
		logger.info("------------------------创建索引----------------------------");
		this.checkIndexAndHtmlPath(indexesPath, filesPath);
		File htmlFile = new File(filesPath);
		File indexesFile = new File(indexesPath);
		List<File> files = new ArrayList<File>();
		this.getFiles(htmlFile, files);
		IndexWriter indexWriter = this.getIndexWriter(indexesFile);

		FieldType fieldType = new FieldType();
		fieldType.setStored(true);
		fieldType.setIndexed(false);
		fieldType.setTokenized(true);
		fieldType.setOmitNorms(false);
		try {
			for (File file : files) {
				if (file.getName().endsWith(".html")
						|| file.getName().endsWith(".htm")) {
					String fileName = file.getName();
					Html html = this.getHtml(file);
					Document document = new Document();
					Field idField = new Field("id", fileName.substring(0,
							fileName.indexOf(".")), fieldType);
					document.add(idField);
					Field titleField = new Field("title", html.getTitle(),
							TextField.TYPE_STORED);
					document.add(titleField);
					Field bodyField = new Field("content", html.getContent(),
							TextField.TYPE_STORED);
					document.add(bodyField);
					Field allSignField = new Field("allSign",
							RoommvConstant.ALL_SIGN, TextField.TYPE_STORED);
					document.add(allSignField);
					Field authorField = new Field("author", html.getAuthor(),
							fieldType);
					document.add(authorField);
					Field createDateField = new Field("createDate",
							html.getCreateDate(), fieldType);
					document.add(createDateField);
					Field pathField = new Field("path",
							file.getCanonicalPath(), fieldType);
					document.add(pathField);
					indexWriter.addDocument(document);
				}
			}
			indexWriter.commit();
		} catch (Exception e) {
			indexWriter.rollback();
		} finally {
			indexWriter.close();
		}
	}

	private Html getHtml(File file) throws IOException, ParserException {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder(2000);
		InputStreamReader inputStreamReader = new InputStreamReader(
				new FileInputStream(file), "utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			sb.append(line);
		}
		inputStreamReader.close();
		String htmlstr = sb.toString();
		Html html = new Html();
		html.setTitle(this.getTile(htmlstr));
		html.setContent(this.getContent(htmlstr));
		html.setCreateDate(this.getCreateDate(htmlstr));
		html.setAuthor(this.getAuthor(htmlstr));
		return html;
	}

	private String getAuthor(String htmlstr) throws ParserException {
		// TODO Auto-generated method stub
		Parser parser = Parser.createParser(htmlstr, "utf-8");
		NodeFilter cssSelectorFilter = new CssSelectorNodeFilter(
				RoommvConstant.AUTHOR_CSSSELECTORNODEFILTER);
		NodeList nodeList = parser.extractAllNodesThatMatch(cssSelectorFilter);
		if (nodeList == null || nodeList.size() == 0) {
			return "";
		} else {
			return nodeList.elementAt(0).toPlainTextString();
		}
	}

	private String getCreateDate(String htmlstr) throws ParserException {
		// TODO Auto-generated method stub
		Parser parser = Parser.createParser(htmlstr, "utf-8");
		NodeFilter cssSelectorFilter = new CssSelectorNodeFilter(
				RoommvConstant.CREATEDATE_CSSSELECTORNODEFILTER);
		NodeList nodeList = parser.extractAllNodesThatMatch(cssSelectorFilter);
		if (nodeList == null || nodeList.size() == 0) {
			return "";
		} else {
			return nodeList.elementAt(0).toPlainTextString();
		}
	}

	private String getContent(String htmlstr) throws ParserException {
		// TODO Auto-generated method stub
		Parser parser = Parser.createParser(htmlstr, "utf-8");
		CssSelectorNodeFilter contentFilter = new CssSelectorNodeFilter(
				RoommvConstant.CONTENT_CSSSELECTORNODEFILTER);
		NodeList nodeList = parser.extractAllNodesThatMatch(contentFilter);
		if (nodeList == null || nodeList.size() == 0) {
			return "";
		} else {
			return nodeList.elementAt(0).toPlainTextString();
		}
	}

	private String getTile(String htmlstr) throws ParserException {
		// TODO Auto-generated method stub
		Parser parser = Parser.createParser(htmlstr, "utf-8");
		CssSelectorNodeFilter titleFilter = new CssSelectorNodeFilter(
				RoommvConstant.TITLE_CSSSELECTORNODEFILTER);
		NodeList nodeList = parser.extractAllNodesThatMatch(titleFilter);
		if (nodeList == null || nodeList.size() == 0) {
			return "";
		} else {
			return nodeList.elementAt(0).toPlainTextString();
		}
	}

	private void checkIndexAndHtmlPath(String indexesPath, String htmlPath) {
		// TODO Auto-generated method stub
		if (StringUtils.isEmpty(indexesPath)) {
			throw new RuntimeException("存放索引文件的地址不能为空");
		}
		if (StringUtils.isEmpty(htmlPath)) {
			throw new RuntimeException("被索引的文件地址不能为空");
		}
		File indexesFile = new File(indexesPath);
		File htmlFile = new File(htmlPath);
		if (!indexesFile.exists()) {
			indexesFile.mkdir();
		}
		if (!htmlFile.exists()) {
			throw new RuntimeException("路径" + htmlPath + "不存在!");
		}
	}

	public List<File> getFiles(File file, List<File> files) {
		if (file.isDirectory()) {
			File[] tempFiles = file.listFiles();
			int length = tempFiles.length;
			for (int i = 0; i < length; i++) {
				getFiles(tempFiles[i], files);
			}
		} else {
			files.add(file);
		}
		return files;
	}


	public IndexWriter getIndexWriter(File indexesFile)
				throws IOException {
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(
					Version.LATEST, analyzer);
		indexWriterConfig.setOpenMode(openMode);
			Directory directory = FSDirectory.open(indexesFile);
		IndexWriter indexWriter = new IndexWriter(directory,
								indexWriterConfig);
			return indexWriter;
		}


	public void optimize() throws IOException {
		logger.info("------------------------优化索引----------------------------");
		File indexesFile = new File(indexesPath);
		IndexWriter htmlIndexWriter = this.getIndexWriter(indexesFile);
		htmlIndexWriter.forceMerge(1);
		htmlIndexWriter.close();
	}

	public void deleteAll() throws IOException {
		logger.info("------------------------删除索引----------------------------");
		File indexesFile = new File(indexesPath);
		IndexWriter htmlIndexWriter = this.getIndexWriter(indexesFile);
		htmlIndexWriter.deleteAll();
		htmlIndexWriter.close();
	}

	public void setAnalyzer(Analyzer analyzer) {
		HtmlIndexer.analyzer = analyzer;
	}

	public String getIndexesPath() {
		return indexesPath;
	}

	public void setIndexesPath(String indexesPath) {
		this.indexesPath = indexesPath;
	}

	public String getFilesPath() {
		return filesPath;
	}

	public void setFilesPath(String filesPath) {
		this.filesPath = filesPath;
	}
}
