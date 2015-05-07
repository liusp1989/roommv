package com.liusp.roommv.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.htmlparser.util.ParserException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liusp.roommv.common.RoommvConstant;
import com.liusp.roommv.index.HtmlSearcher;
import com.liusp.roommv.service.webPage.IWebPageService;
import com.liusp.roommv.vo.Html;
import com.liusp.roommv.vo.Page;

@Controller(value = "webPageController")
@RequestMapping("/webpage")
public class WebPageController {
	@Resource()
	private IWebPageService webPageService;

	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
	public String webPageList(ModelMap map, String searchInfo)
			throws ParserException,
			IOException, ParseException, InvalidTokenOffsetsException,
			NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException {
		Page page = new Page();
			page.setStart(0);
		if (searchInfo != null) {
			searchInfo = URLDecoder.decode(searchInfo, "UTF-8");
		}
		List<Html> htmls = this.searchHtmls(searchInfo, page);
		Integer start = htmls.size();
		map.put("htmls", htmls);
		map.put("searchInfo", searchInfo);
		map.put("start", start);
		return "webpage/list";
	}


	@RequestMapping(value = { "/list/more" }, method = { RequestMethod.GET })
	public @ResponseBody
	Map<String, Object> webPageListMore(Integer start, Integer count,
			String searchInfo)
			throws SQLException, ParserException, IOException, ParseException,
			InvalidTokenOffsetsException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Page page = new Page();
		if (start != null) {
			page.setStart(start);
		} else {
			page.setStart(0);
		}
		if (count != null) {
			page.setCount(count);
		}
		if (searchInfo != null) {
			searchInfo = URLDecoder.decode(searchInfo, "UTF-8");
		}
		List<Html> htmls = this.searchHtmls(searchInfo, page);
		resultMap.put("htmls", htmls);
		resultMap.put("count", htmls.size());
		return resultMap;
	}

	private List<Html> searchHtmls(String searchInfo, Page page)
			throws ParserException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException, IOException,
			ParseException, InvalidTokenOffsetsException {
		// TODO Auto-generated method stub
		Analyzer analyzer = new PaodingAnalyzer();
		HtmlSearcher htmlSearcher = new HtmlSearcher(
				RoommvConstant.HTML_INDEXES_PATH);
		htmlSearcher.setAnalyzer(analyzer);
		List<Html> htmls = new ArrayList<Html>();
		if (searchInfo == null || StringUtils.trimWhitespace(searchInfo) == "") {
			htmls = htmlSearcher.searchInfoByPage(new String[] { "allSign" },
					RoommvConstant.ALL_SIGN, page);
		} else {
			// searchInfo = URLDecoder.decode(searchInfo, "UTF-8");
			htmls = htmlSearcher.searchInfoByPage(new String[] { "content",
					"title" },
					searchInfo, page);
		}

		return htmls;
	}
}
