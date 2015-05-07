package com.liusp.roommv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller(value = "searchController")
public class SearchController {
	public @ResponseBody boolean createPagesIndexes(){
	return true;
}
}
