package com.liusp.roommv.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liusp.roommv.entity.html.HtmlInfo;
import com.liusp.roommv.service.htmlInfo.IHtmlInfoService;
import com.liusp.roommv.vo.AjaxResult;
import com.liusp.roommv.vo.Page;

@Controller(value = "htmlInfoController")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/htmlInfo")
public class HtmlInfoController {
	public static final Logger logger = Logger
			.getLogger(HtmlInfoController.class);
	@Resource(name = "htmlInfoService")
	private IHtmlInfoService htmlInfoService;

	@RequestMapping("list")
	public String list(ModelMap model, Page page) {
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("page", page);
		List<HtmlInfo> htmlInfos = htmlInfoService.getHtmlInfos(criteria);
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
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("id", id);
		HtmlInfo htmlInfo = (HtmlInfo) htmlInfoService.getHtmlInfos(criteria)
				.get(0);
		model.put("htmlInfo", htmlInfo);
		return "common/editor";
	}

	@RequestMapping("audit")
	public String audit(@ModelAttribute("htmlInfo") HtmlInfo htmlInfo,
			ModelMap modelMap) throws UnsupportedEncodingException {
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("id", htmlInfo.getId());
		HtmlInfo orightmlInfo = (HtmlInfo) htmlInfoService
				.getHtmlInfos(criteria);
		orightmlInfo.setUpdateDate(new Date());
		orightmlInfo.setAuditStatus(htmlInfo.getAuditStatus());
		orightmlInfo.setRemark(htmlInfo.getRemark());
		try {
			htmlInfoService.auditHtmlInfo(orightmlInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("操作失败", e);
		}
		modelMap.put("htmlInfo", htmlInfo);
		return "common/view";
	}

	@RequestMapping("view/{id}")
	public String view(@PathVariable String id, ModelMap model)
			throws UnsupportedEncodingException {
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("id", id);
		HtmlInfo htmlInfo = (HtmlInfo) htmlInfoService.getHtmlInfos(criteria)
				.get(0);
		model.put("htmlInfo", htmlInfo);
		return "common/view";
	}

	@RequestMapping("imageUrl")
	public @ResponseBody
	AjaxResult getImageUrl(String id) {
		AjaxResult ajaxResult = new AjaxResult();
		HtmlInfo htmlInfo;
		try {
			Map<String, Object> criteria = new HashMap<String, Object>();
			criteria.put("id", id);
			List<HtmlInfo> htmlInfos = htmlInfoService.getHtmlInfos(criteria);
			if (!CollectionUtils.isEmpty(htmlInfos)) {
				htmlInfo = htmlInfos.get(0);
				ajaxResult.setResultCode(AjaxResult.ResultCode.SUCCESS);
				ajaxResult.setValue(htmlInfo.getImageUrl());
			} else {
				ajaxResult.setResultCode(AjaxResult.ResultCode.SUCCESS);
				ajaxResult.setValue(null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ajaxResult.setResultCode(AjaxResult.ResultCode.FAILED);
			logger.error("操作失败", e);
		}
		return ajaxResult;
	}

	@RequestMapping("save")
	public String save(@ModelAttribute("htmlInfo") HtmlInfo htmlInfo,
			ModelMap model) throws IOException {
		try {
			htmlInfo.setCreateDate(new Date());
			htmlInfo.setUpdateDate(new Date());
			htmlInfoService.save(htmlInfo);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("操作失败", e);
		}
		model.put("htmlInfo", htmlInfo);
		return "common/editor";
	}

	@RequestMapping("cancel")
	public @ResponseBody
	AjaxResult cancel(String id) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			HtmlInfo htmlInfo = new HtmlInfo();
			htmlInfo.setId(id);
			htmlInfoService.deleteHtmlInfo(htmlInfo);
			ajaxResult.setResultCode(AjaxResult.ResultCode.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ajaxResult.setResultCode(AjaxResult.ResultCode.FAILED);
			logger.error("操作失败", e);
		}
		return ajaxResult;
	}

}
