package com.liusp.roommv.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liusp.roommv.common.RoommvConstant;
import com.liusp.roommv.entity.html.HtmlInfo;
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

	@RequestMapping("edit")
	public String edit(String id, ModelMap model) {
		DetachedCriteria criteria = DetachedCriteria.forClass(HtmlInfo.class);
		Criterion criterion = Restrictions.eq("id", id);
		criteria.add(criterion);
		HtmlInfo htmlInfo = (HtmlInfo) htmlInfoService.findObjects(criteria)
				.get(0);
		model.put("htmlInfo", htmlInfo);
		return "common/editor";
	}

	@RequestMapping("save")
	public String save(String id, String bodyContent, String title)
			throws IOException {
		String destFilePath = RoommvConstant.HTML_FILES_TEMPPATH;
		File destFilePathFile = new File(destFilePath);
		if (!destFilePathFile.exists()) {
				destFilePathFile.mkdirs();
		}
		String destFileName = new Date().getTime()+".html";
		File destFile = new File(destFilePath, destFileName);
		DetachedCriteria criteria = DetachedCriteria.forClass(HtmlInfo.class);
		Criterion criterion = Restrictions.eq("id", id);
		criteria.add(criterion);
		HtmlInfo htmlInfo = (HtmlInfo) htmlInfoService.findObjects(criteria)
				.get(0);
		htmlInfo.setUpdateDate(new Date());
		htmlInfo.setTempHtmlUrl(destFilePath+destFileName);
		htmlInfoService.updateObject(htmlInfo);
		StringBuilder titleBuilder = new StringBuilder(100);
		titleBuilder.append("<!DOCTYPE HTML>\n");
		titleBuilder.append("<head>\n");
		titleBuilder
				.append("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>\n");
		titleBuilder.append("<title>").append(title).append("</title>\n");
		titleBuilder
				.append("<link href='../css/bootstrap.min.css' type='text/css' rel=''stylesheet' />\n");
		titleBuilder
				.append("<link href='../css/bootstrap-theme.min.css' type='text/css' rel='stylesheet' />\n");
		titleBuilder.append("</head>\n");
		StringBuilder bodyBuilder = new StringBuilder(4000);
		bodyBuilder.append("<body>\n");
		bodyBuilder.append("<div name='content'>\n");
		bodyBuilder.append(bodyContent);
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
				.append("<script src='http://libs.baidu.com/jquery/1.11.1/jquery.js' type=''text/javascript'></script>\n");
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

		FileWriter writer = new FileWriter(destFile);
		writer.write(htmlStr);
		writer.flush();
		writer.close();
		return "common/list";
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
