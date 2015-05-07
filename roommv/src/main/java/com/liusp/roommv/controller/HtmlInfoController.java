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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liusp.roommv.common.RoommvConstant.AuditStatus;
import com.liusp.roommv.entity.html.HtmlInfo;
import com.liusp.roommv.entity.html.HtmlInfo.HandleStatus;
import com.liusp.roommv.service.htmlInfo.IHtmlInfoService;
import com.liusp.roommv.vo.AjaxResult;
import com.liusp.roommv.vo.Page;

@Controller(value = "htmlInfoController")
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
		criteria.put("nehandleStatus", HandleStatus.EDIT_ING.getCode());
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

	@RequestMapping("submitOraudit")
	public String submitOraudit(@ModelAttribute("htmlInfo") HtmlInfo htmlInfo,
			ModelMap modelMap) throws UnsupportedEncodingException {
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("id", htmlInfo.getId());
		HtmlInfo orightmlInfo = (HtmlInfo) htmlInfoService.getHtmlInfos(
				criteria).get(0);
		orightmlInfo.setUpdateDate(new Date());
		orightmlInfo.setAuditStatus(htmlInfo.getAuditStatus());
		orightmlInfo.setHandleStatus(htmlInfo.getHandleStatus());
		orightmlInfo.setRemark(htmlInfo.getRemark());
		try {
			htmlInfoService.auditHtmlInfo(orightmlInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("操作失败", e);
		}
		modelMap.put("htmlInfo", htmlInfo);
		return "redirect:view/" + orightmlInfo.getId();
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

	@RequestMapping("save")
	public String save(@ModelAttribute("htmlInfo") HtmlInfo htmlInfo,
			ModelMap model) throws IOException {
		try {
			htmlInfo.setCreateDate(new Date());
			htmlInfo.setUpdateDate(new Date());
			htmlInfo.setHandleStatus(HandleStatus.EDIT_SAVE.getCode());
			htmlInfo.setAuditStatus(AuditStatus.AUDIT_ING.getCode());
			htmlInfoService.saveAndWriteFile(htmlInfo);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("操作失败", e);
		}
		model.put("htmlInfo", htmlInfo);
		return "redirect:view/" + htmlInfo.getId();
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
