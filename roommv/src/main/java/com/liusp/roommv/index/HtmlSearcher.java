package com.liusp.roommv.index;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.TextFragment;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.FSDirectory;
import org.htmlparser.util.ParserException;
import org.springframework.util.CollectionUtils;

import com.liusp.roommv.common.RoommvConstant;
import com.liusp.roommv.vo.Html;
import com.liusp.roommv.vo.Page;

public class HtmlSearcher implements Searcher {
	public static final Logger logger = Logger.getLogger(HtmlSearcher.class);
	private String indexesPath;
	private static Analyzer analyzer;

	public HtmlSearcher(String indexesPath) {
		this.indexesPath = indexesPath;
	}

	@Override
	public void setAnalyzer(Analyzer analyzer) {
		// TODO Auto-generated method stub
		HtmlSearcher.analyzer = analyzer;
	}


	@Override
	public List<Html> searchInfoByPage(String[] fields, String info, Page page)
			throws IOException, ParserException, ParseException,
			InvalidTokenOffsetsException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		if (fields == null || fields.length == 0) {
			throw new RuntimeException("查询字段不能为空！");
		}
		DirectoryReader reader = DirectoryReader.open(FSDirectory
				.open(new File(indexesPath)));
		IndexSearcher searcher = new IndexSearcher(reader);
		QueryParser parser = new MultiFieldQueryParser(fields, analyzer);
		Query query = parser.parse(info);
		SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter(
				"<font color=\"red\">", "</font>");
		Highlighter highlighter = new Highlighter(simpleHTMLFormatter,
				new QueryScorer(query));
		ScoreDoc lastScoreDoc = this.getlastScoreDoc(searcher, query, page);
		TopDocs topDocs = searcher.searchAfter(lastScoreDoc, query,
				page.getCount());
		int totalHits = topDocs.totalHits;
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		List<Html> htmls = new ArrayList<Html>();
		if (scoreDocs != null && scoreDocs.length != 0) {
			for (ScoreDoc scoreDoc : scoreDocs) {
				int doc = scoreDoc.doc;
				htmls.add(this.getHtml(searcher, fields, doc, highlighter));
			}
		}
		reader.close();
		return htmls;
	}

	private Html getHtml(IndexSearcher searcher, String[] fields, int doc,
			Highlighter highlighter) throws IOException,
			InvalidTokenOffsetsException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		Document document = searcher.doc(doc);
		Html html = new Html();
		List<IndexableField> indexableFields = document.getFields();
		if (!CollectionUtils.isEmpty(indexableFields)) {
			for (IndexableField indexableField : indexableFields) {
				String fieldName = indexableField.name();
				String fieldValue = document.get(fieldName);
				for (String field : fields) {
					if (field.equals(fieldName)) {
						TokenStream tokenStream = TokenSources
								.getAnyTokenStream(searcher.getIndexReader(),
										doc, field, new PaodingAnalyzer());
						TextFragment[] fieldValueFrag = highlighter
								.getBestTextFragments(tokenStream, fieldValue,
										true,
										RoommvConstant.TEXT_FRAGMENT_TIMES);
						tokenStream.close();
						StringBuilder fieldValueBuilder = new StringBuilder();
						for (int j = 0; j < fieldValueFrag.length; j++) {
							if ((fieldValueFrag[j] != null)
									&& (fieldValueFrag[j].getScore() > 0)) {
								fieldValueBuilder.append(fieldValueFrag[j]
										.toString());
							}
						}
						int fieldValueBuilderLength = fieldValueBuilder
								.length();
						if (fieldValueBuilderLength > 0) {
							fieldValue = fieldValueBuilder.toString();
						}
					}
					this.setValueIntoObj(html, fieldName, fieldValue);
				}
			}
		}
		return html;
	}

	private void setValueIntoObj(Html html, String feildName, String fieldValue)
			throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		Field field = html.getClass().getDeclaredField(feildName);
		field.setAccessible(true);
		if (fieldValue != null) {
			int length = fieldValue.length();
			if (length > RoommvConstant.HTML_CONTENT_LENGTH) {
				fieldValue = fieldValue.substring(0,
						RoommvConstant.HTML_CONTENT_LENGTH);
			}
		}
		field.set(html, fieldValue);
		field.setAccessible(false);
	}

	private ScoreDoc getlastScoreDoc(IndexSearcher searcher, Query query,
			Page page) throws IOException {
		// TODO Auto-generated method stub
		int end = page.getEnd();
		int count = page.getCount();
		if (end <= count) {
			return null;
		} else {
			int lastEnd = page.getEnd() - page.getCount();
			TopDocs topDocs = searcher.search(query, lastEnd);
			return topDocs.scoreDocs[lastEnd - 1];
		}

	}

}
