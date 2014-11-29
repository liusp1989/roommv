package com.liusp.roommv.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "uEditorController")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/ueditor")
public class UEditorController {
	public static final Logger logger = Logger
			.getLogger(UEditorController.class);

	@RequestMapping("/controller")
	public String controller() {

		return "common/controller";
	}
}
