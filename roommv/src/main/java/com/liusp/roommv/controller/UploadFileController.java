package com.liusp.roommv.controller;

import java.io.File;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.liusp.roommv.common.RoommvConstant;
import com.liusp.roommv.common.RoommvConstant.AuditStatus;
import com.liusp.roommv.entity.html.HtmlInfo;
import com.liusp.roommv.service.htmlInfo.IHtmlInfoService;
import com.liusp.roommv.vo.AjaxResult;

@Controller(value = "upLoadFileController")
@RequestMapping("/upload")
public class UploadFileController {
	public static final Logger logger = Logger
			.getLogger(UploadFileController.class);
	@Resource(name = "htmlInfoService")
	private IHtmlInfoService htmlInfoService;

	@RequestMapping("htmlImage")
	public @ResponseBody
	AjaxResult uploadHtmlImage(@RequestParam MultipartFile file, String id,
			HttpServletRequest request) throws Exception {
		AjaxResult ajaxResult = new AjaxResult();
		String destFilePath = RoommvConstant.WEB_ROOT + "/upload/htmlimage/";
		File destFilePathFile = new File(destFilePath);
		if (!destFilePathFile.exists()) {
			destFilePathFile.mkdirs();
		}
		String oriFileName = file.getOriginalFilename();
		String destFileName = new Date().getTime()
				+ oriFileName.substring(oriFileName.lastIndexOf("."));
		File destFile = new File(destFilePath, destFileName);
		file.transferTo(destFile);
		String imageUrl = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/upload/htmlimage" + "/" + destFileName;
		HtmlInfo htmlInfo = new HtmlInfo();
		htmlInfo.setId(id);
		htmlInfo.setCreateDate(new Date());
		htmlInfo.setCreator("");
		htmlInfo.setUpdateDate(new Date());
		htmlInfo.setUpdator("");
		htmlInfo.setImageUrl(imageUrl);
		htmlInfo.setIpInfo(request.getRemoteAddr());
		htmlInfo.setHandleStatus(HtmlInfo.HandleStatus.EDIT_ING.getCode());
		htmlInfo.setAuditStatus(AuditStatus.AUDIT_ING.getCode());
		if (htmlInfoService.getHtmlInfoById(id) == null) {
			htmlInfoService.save(htmlInfo);
		} else {
			htmlInfoService.updateHtmlInfo(htmlInfo);
		}
		ajaxResult.setResultCode(AjaxResult.ResultCode.SUCCESS);
		ajaxResult.setValue(htmlInfo);
		return ajaxResult;
	}

	@RequestMapping("uploadFrame")
	public String uploadFrame() {
		return "common/uploadFrame";
	}

	@ExceptionHandler(value = Exception.class)
	public @ResponseBody
	AjaxResult handleException(Exception e, HttpServletRequest requestl) {
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setResultCode(AjaxResult.ResultCode.FAILED);
		if (e instanceof MaxUploadSizeExceededException) {
			ajaxResult.setMsg("上传图片超过限制大小");
		} else {
			ajaxResult.setMsg("系统异常");
		}
		logger.error("系统异常", e);
		return ajaxResult;
	}
}
