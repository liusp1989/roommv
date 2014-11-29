package com.liusp.roommv.controller;

import java.io.File;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.liusp.roommv.common.RoommvConstant;
import com.liusp.roommv.entity.html.HtmlInfo;
import com.liusp.roommv.service.htmlInfo.IHtmlInfoService;
import com.liusp.roommv.vo.AjaxResult;

@Controller(value = "upLoadFileController")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/upload")
public class UploadFileController {
	public static final Logger logger = Logger
			.getLogger(UploadFileController.class);
	@Resource(name = "htmlInfoService")
	private IHtmlInfoService htmlInfoService;
	@RequestMapping("htmlImage")
	public String uploadHtmlImage(@RequestParam MultipartFile file,
 String id,
			HttpServletRequest request, ModelMap model) {
		AjaxResult ajaxResult = new AjaxResult();
		String destFilePath = RoommvConstant.WEB_ROOT + "/upload/htmlimage/";
		File destFilePathFile = new File(destFilePath);
		try {
		if (!destFilePathFile.exists()) {
				destFilePathFile.mkdirs();
		}
		String oriFileName = file.getOriginalFilename();
			String destFileName = new Date().getTime()
					+ oriFileName.substring(oriFileName.lastIndexOf("."));
		File destFile = new File(destFilePath, destFileName);
			file.transferTo(destFile);
			String imageUrl = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/upload/htmlimage" + "/"
					+ destFileName;
			HtmlInfo htmlInfo = new HtmlInfo();
			htmlInfo.setId(id);
			htmlInfo.setCreateDate(new Date());
			htmlInfo.setCreator("");
			htmlInfo.setUpdateDate(new Date());
			htmlInfo.setUpdator("");
			htmlInfo.setImageUrl(imageUrl);
			htmlInfo.setIpInfo(request.getRemoteAddr());
			if (htmlInfoService.findObjectById(id) == null) {
				htmlInfoService.addObject(htmlInfo);
			} else {
				htmlInfoService.updateObject(htmlInfo);
			}
			ajaxResult.setResultCode(AjaxResult.ResultCode.SUCCESS);
			ajaxResult.setValue(imageUrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ajaxResult.setResultCode(AjaxResult.ResultCode.FAILED);
			logger.error("系统内部错误", e);
		}
		model.put("result", ajaxResult);
		return "common/uploadFrame";
	}

	@RequestMapping("uploadFrame")
	public String uploadFrame() {
		return "common/uploadFrame";
	}
}
