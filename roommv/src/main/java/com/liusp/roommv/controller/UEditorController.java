package com.liusp.roommv.controller;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liusp.roommv.entity.html.HtmlInfo;

@Controller(value = "uEditorController")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RequestMapping("/ueditor")
public class UEditorController {
	public static final Logger logger = Logger
			.getLogger(UEditorController.class);

	@RequestMapping("")
	public String ueditor(ModelMap model) {
		String id = UUID.randomUUID().toString();
		HtmlInfo htmlInfo = new HtmlInfo();
		htmlInfo.setId(id);
		model.put("htmlInfo", htmlInfo);
		return "common/editor";
	}

	@RequestMapping("/controller")
	public String controller() {

		return "common/controller";
	}
}
