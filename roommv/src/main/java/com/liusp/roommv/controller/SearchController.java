package com.liusp.roommv.controller;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller(value = "searchController")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SearchController {
	public @ResponseBody boolean createPagesIndexes(){
	return true;
}
}
