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
					OpenMode.APPEND);
			htmlIndexer.setAnalyzer(analyzer);
			htmlIndexer.createSearchIndexes();
		}
	}

	@Override
	public void save(HtmlInfo htmlInfo) throws Exception {
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
		StringBuilder titleBuilder = new StringBuilder(100);
		titleBuilder
				.append("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'\n");
		titleBuilder.append("'http://www.w3.org/TR/html4/loose.dtd'>\n");
		titleBuilder.append("<head>\n");
		titleBuilder
				.append("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>\n");
		titleBuilder.append("<title>").append(title).append("</title>\n");
		titleBuilder
				.append("<link href='../css/bootstrap.min.css' type='text/css' rel='stylesheet' />\n");
		titleBuilder
				.append("<link href='../css/bootstrap-theme.min.css' type='text/css' rel='stylesheet' />\n");
		titleBuilder.append("</head>\n");
		StringBuilder bodyBuilder = new StringBuilder(4000);
		bodyBuilder.append("<body>\n");
		bodyBuilder.append("<div name='content'>\n");
		bodyBuilder.append(content);
		bodyBuilder.append("<p name='createDate'>");
		bodyBuilder.append(htmlInfo.getCreateDate());
		bodyBuilder.append("</p>\n");
		bodyBuilder.append("<p name='author'>");
		bodyBuilder.append(htmlInfo.getCreator());
		bodyBuilder.append("</p>\n");
		bodyBuilder.append("</div>\n");
		bodyBuilder.append("<body>\n");

		StringBuilder footerBuilder = new StringBuilder(200);
		footerBuilder.append("</html>\n");
		footerBuilder
				.append("<script src='http://libs.baidu.com/jquery/1.11.1/jquery.js' type='text/javascript'></script>\n");
		footerBuilder.append("<script type='text/javascript'>\n");
		footerBuilder
				.append("window.jQuery||document.write('<script src=&quot;../js/jquery-1.11.1.min.js&quot;><\\/script>');\n");
		footerBuilder.append("</script>\n");
		footerBuilder
				.append("<script src='http://libs.baidu.com/bootstrap/3.2.0/js/bootstrap.min.js' type='text/javascript'></script>\n");
		footerBuilder.append("<script type='text/javascript'>\n");
		footerBuilder
				.append("window.jQuery||document.write('<script src=&quot;../js/bootstrap.min.js&quot;><\\/script>');\n");
		footerBuilder.append("</script>\n");
		String htmlStr = titleBuilder.toString() + bodyBuilder.toString()
				+ footerBuilder.toString();
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

}
