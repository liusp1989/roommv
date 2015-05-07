package com.liusp.roommv.service.htmlInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.liusp.roommv.common.RoommvConstant;
import com.liusp.roommv.dao.htmlInfo.IHtmlInfoDao;
import com.liusp.roommv.entity.html.HtmlInfo;
import com.liusp.roommv.entity.html.HtmlTemplate;
import com.liusp.roommv.index.HtmlIndexer;

@Service(value = "htmlInfoService")
public class HtmlInfoServiceImpl implements IHtmlInfoService {
	public static final Logger logger = Logger
			.getLogger(HtmlInfoServiceImpl.class);
	@Resource()
	private IHtmlInfoDao htmlInfoDao;

	@Override
	public void saveOrUpdateHtmlInfo(HtmlInfo htmlInfo) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("id", htmlInfo.getId());
		List<HtmlInfo> htmlInfos = htmlInfoDao.queryHtmlInfos(criteria);
		if (htmlInfos.isEmpty()) {
			htmlInfoDao.addHtmlInfo(htmlInfo);
		} else {
			htmlInfoDao.updateHtmlInfo(htmlInfo);
		}
	}

	@Override
	public void auditHtmlInfo(HtmlInfo orightmlInfo) throws IOException {
		// TODO Auto-generated method stub
		htmlInfoDao.updateHtmlInfo(orightmlInfo);
		if (RoommvConstant.AuditStatus.AUDIT_PASS.getCode().equals(
				orightmlInfo.getAuditStatus())) {
			String htmlId = orightmlInfo.getHtmlId();
			String htmlName = htmlId + ".html";
			File tempFile = new File(RoommvConstant.HTML_FILES_TEMPPATH
					+ htmlName);
			String destFilePath = RoommvConstant.HTML_FILES_PATH + htmlName;
			File destFile = new File(destFilePath);
			FileUtils.copyFile(tempFile, destFile);
			Analyzer analyzer = new PaodingAnalyzer();
			HtmlIndexer htmlIndexer = new HtmlIndexer(
					RoommvConstant.HTML_INDEXES_PATH, destFilePath,
					OpenMode.CREATE_OR_APPEND);
			htmlIndexer.setAnalyzer(analyzer);
			htmlIndexer.createSearchIndexes();
		}
	}

	@Override
	public void save(HtmlInfo htmlInfo) throws Exception {
		// TODO Auto-generated method stub
		htmlInfoDao.addHtmlInfo(htmlInfo);
	}

	@Override
	public List<HtmlInfo> getHtmlInfos(Map<String, Object> criteria) {
		// TODO Auto-generated method stub
		return htmlInfoDao.queryHtmlInfos(criteria);
	}

	@Override
	public void deleteHtmlInfo(HtmlInfo htmlInfo) {
		// TODO Auto-generated method stub
		htmlInfoDao.deleteHtmlInfo(htmlInfo);
	}


	@Override
	public HtmlInfo getHtmlInfoById(String id) {
		// TODO Auto-generated method stub
		return htmlInfoDao.queryHtmlInfoById(id);
	}

	@Override
	public void updateHtmlInfo(HtmlInfo htmlInfo) {
		// TODO Auto-generated method stub
		htmlInfoDao.updateHtmlInfo(htmlInfo);
	}

	@Override
	public List<HtmlTemplate> getHtmlTemplate(Map<String, Object> criteria) {
		// TODO Auto-generated method stub
		return htmlInfoDao.queryHtmlTemplate(criteria);
	}

	@Override
	public void saveAndWriteFile(HtmlInfo htmlInfo) throws Exception {
		// TODO Auto-generated method stub
		String content = htmlInfo.getContent();
		String htmlId = htmlInfo.getHtmlId();
		String title = htmlInfo.getTitle();
		String destFilePath = RoommvConstant.HTML_FILES_TEMPPATH;
		File destFilePathFile = new File(destFilePath);

		if (!destFilePathFile.exists()) {
			destFilePathFile.mkdirs();
		}
		if (StringUtils.isEmpty(htmlId)) {
			htmlId = String.valueOf(new Date().getTime());
		}
		String destFileName = htmlId + ".html";
		File destFile = new File(destFilePath, destFileName);

		htmlInfo.setUpdateDate(new Date());
		htmlInfo.setHtmlId(htmlId);
		this.saveOrUpdateHtmlInfo(htmlInfo);
		String htmlStr = this.getHtmlStr(content);
		OutputStreamWriter writer = new OutputStreamWriter(
				new FileOutputStream(destFile), "UTF-8");
		try {
			writer.write(htmlStr);
			writer.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("操作失败", e);
		} finally {
			writer.close();
		}
	}
	private String getHtmlStr(String content) {
		StringBuilder htmlBuilder = new StringBuilder(4000);
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("useStatus", 1);
		HtmlTemplate htmlTemplate = this.getHtmlTemplate(criteria).get(0);

		htmlBuilder.append(htmlTemplate.getBeforeHeader());
		htmlBuilder.append(htmlTemplate.getAfterHeader());

		htmlBuilder.append(htmlTemplate.getBeforeContent());
		htmlBuilder.append(content);
		htmlBuilder.append(htmlTemplate.getAfterContent());

		htmlBuilder.append(htmlTemplate.getBeforeFooter());
		htmlBuilder.append(htmlTemplate.getAfterFooter());
		String htmlStr = htmlBuilder.toString();
		return htmlStr;
	}

}
