package com.liusp.roommv.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liusp.roommv.common.RoommvConstant;
import com.liusp.roommv.entity.html.HtmlInfo;
import com.liusp.roommv.index.HtmlIndexer;
import com.liusp.roommv.service.htmlInfo.IHtmlInfoService;
import com.liusp.roommv.vo.AjaxResult;

@Controller(value = "htmlInfoController")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/htmlInfo")
public class HtmlInfoController {
	public static final Logger logger = Logger
			.getLogger(HtmlInfoController.class);
	@Resource(name = "htmlInfoService")
	private IHtmlInfoService htmlInfoService;
	@RequestMapping("list")
	public String list(ModelMap model) {
		List<Object> htmlInfos = htmlInfoService.getAllObjectList(
				HtmlInfo.class, null);
		model.put("htmlInfos", htmlInfos);
		return "common/list";
	}

	@RequestMapping("add")
	public String add(ModelMap model) {
		String id = UUID.randomUUID().toString();
		HtmlInfo htmlInfo = new HtmlInfo();
		htmlInfo.setId(id);
		model.put("htmlInfo", htmlInfo);
		return "common/editor";
	}

	@RequestMapping("edit/{id}")
	public String edit(@PathVariable String id, ModelMap model)
			throws UnsupportedEncodingException {
		DetachedCriteria criteria = DetachedCriteria.forClass(HtmlInfo.class);
		Criterion criterion = Restrictions.eq("id", id);
		criteria.add(criterion);
		HtmlInfo htmlInfo = (HtmlInfo) htmlInfoService.findObjects(criteria)
				.get(0);
		if (!StringUtils.isEmpty(htmlInfo.getContent())) {
		htmlInfo.setContent(URLEncoder.encode(htmlInfo.getContent(), "UTF-8"));
		}
		model.put("htmlInfo", htmlInfo);
		return "common/editor";
	}

	@RequestMapping("audit")
	public String audit(@ModelAttribute("htmlInfo") HtmlInfo htmlInfo,
			ModelMap modelMap)
			throws UnsupportedEncodingException {
		DetachedCriteria criteria = DetachedCriteria.forClass(HtmlInfo.class);
		Criterion criterion = Restrictions.eq("id", htmlInfo.getId());
		criteria.add(criterion);
		HtmlInfo orightmlInfo = (HtmlInfo) htmlInfoService
				.findObjects(criteria)
				.get(0);
		orightmlInfo.setAuditStatus(orightmlInfo.getAuditStatus());
		orightmlInfo.setRemark(orightmlInfo.getRemark());
		try {
			htmlInfoService.updateObject(orightmlInfo);
			if (RoommvConstant.AuditStatus.AUDIT_PASS.getCode().equals(
					htmlInfo.getAuditStatus())) {
			String htmlId = htmlInfo.getHtmlId();
			String htmlName = htmlId + ".html";
			File tempFile = new File(RoommvConstant.HTML_FILES_TEMPPATH
					+ htmlName);
			String destFilePath = RoommvConstant.HTML_FILES_PATH + htmlName;
			File destFile = new File(destFilePath);
			FileUtils.copyFile(tempFile, destFile);
			Analyzer analyzer = new PaodingAnalyzer();
			HtmlIndexer htmlIndexer = new HtmlIndexer(
					RoommvConstant.HTML_INDEXES_PATH, destFilePath);
			htmlIndexer.setAnalyzer(analyzer);
			htmlIndexer.createSearchIndexes();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("操作失败", e);
		}
		modelMap.put("htmlInfo", htmlInfo);
		return "common/view";
	}
	@RequestMapping("view/{id}")
	public String view(@PathVariable  String id, ModelMap model)
			throws UnsupportedEncodingException {
		DetachedCriteria criteria = DetachedCriteria.forClass(HtmlInfo.class);
		Criterion criterion = Restrictions.eq("id", id);
		criteria.add(criterion);
		HtmlInfo htmlInfo = (HtmlInfo) htmlInfoService.findObjects(criteria)
				.get(0);
		if (!StringUtils.isEmpty(htmlInfo.getContent())) {
		htmlInfo.setContent(URLEncoder.encode(htmlInfo.getContent(), "UTF-8"));
		}
		model.put("htmlInfo", htmlInfo);
		return "common/view";
	}
	@RequestMapping("imageUrl")
	public @ResponseBody
	AjaxResult getImageUrl(String id) {
		AjaxResult ajaxResult = new AjaxResult();
		HtmlInfo htmlInfo;
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(HtmlInfo.class);
			Criterion criterion = Restrictions.eq("id", id);
			criteria.add(criterion);
			htmlInfo = (HtmlInfo) htmlInfoService.findObjects(criteria).get(0);
			ajaxResult.setResultCode(AjaxResult.ResultCode.SUCCESS);
			ajaxResult.setValue(htmlInfo.getImageUrl());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ajaxResult.setResultCode(AjaxResult.ResultCode.FAILED);
			logger.error("操作失败", e);
		}
		return ajaxResult;
	}

	@RequestMapping("save")
	public @ResponseBody
	AjaxResult save(String id, String bodyContent, String title, String htmlId)
			throws IOException {
		String bodyContentStr = URLDecoder.decode(bodyContent, "UTF-8");
		bodyContentStr = bodyContentStr.replace("\"", "\'");
		String titleStr = URLDecoder.decode(title, "UTF-8");
		AjaxResult ajaxResult = new AjaxResult();
		String destFilePath = RoommvConstant.HTML_FILES_TEMPPATH;
		File destFilePathFile = new File(destFilePath);
		try {
			if (!destFilePathFile.exists()) {
				destFilePathFile.mkdirs();
			}
			if (StringUtils.isEmpty(htmlId)) {
				htmlId = String.valueOf(new Date().getTime());
			}
			String destFileName = htmlId + ".html";
			File destFile = new File(destFilePath, destFileName);
			HtmlInfo htmlInfo = new HtmlInfo();
			htmlInfo.setId(id);
			htmlInfo.setUpdateDate(new Date());
			htmlInfo.setTitle(titleStr);
			htmlInfo.setHtmlId(htmlId);
			htmlInfo.setContent(bodyContentStr);
			htmlInfoService.saveOrupdateObject(htmlInfo);
			StringBuilder titleBuilder = new StringBuilder(100);
			titleBuilder
					.append("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'\n");
			titleBuilder.append("'http://www.w3.org/TR/html4/loose.dtd'>\n");
			titleBuilder.append("<head>\n");
			titleBuilder
					.append("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>\n");
			titleBuilder.append("<title>")
					.append(URLDecoder.decode(titleStr, "UTF-8"))
					.append("</title>\n");
			titleBuilder
					.append("<link href='../css/bootstrap.min.css' type='text/css' rel='stylesheet' />\n");
			titleBuilder
					.append("<link href='../css/bootstrap-theme.min.css' type='text/css' rel='stylesheet' />\n");
			titleBuilder.append("</head>\n");
			StringBuilder bodyBuilder = new StringBuilder(4000);
			bodyBuilder.append("<body>\n");
			bodyBuilder.append("<div name='content'>\n");
			bodyBuilder.append(URLDecoder.decode(bodyContentStr, "UTF-8"));
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
				ajaxResult.setResultCode(AjaxResult.ResultCode.SUCCESS);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ajaxResult.setResultCode(AjaxResult.ResultCode.FAILED);
				logger.error("操作失败", e);
			} finally {
				writer.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ajaxResult.setResultCode(AjaxResult.ResultCode.FAILED);
			logger.error("操作失败", e);
		}

		return ajaxResult;
	}

	@RequestMapping("cancel")
	public @ResponseBody
	AjaxResult cancel(String id) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			HtmlInfo object = new HtmlInfo();
			object.setId(id);
			htmlInfoService.deleteObject(object);
			ajaxResult.setResultCode(AjaxResult.ResultCode.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ajaxResult.setResultCode(AjaxResult.ResultCode.FAILED);
			logger.error("操作失败", e);
		}
		return ajaxResult;
	}

}
