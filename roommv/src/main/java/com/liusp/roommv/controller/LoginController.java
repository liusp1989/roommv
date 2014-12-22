package com.liusp.roommv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "loginController")
public class LoginController {
	@RequestMapping(value = { "/" }, method = { RequestMethod.GET })
	public String index() {
		return "main";
	}
}
