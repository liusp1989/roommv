package com.liusp.roommv.controller;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "loginController")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginController {
	@RequestMapping(value = { "/" }, method = { RequestMethod.GET })
	public String index() {
		return "main";
	}
}
