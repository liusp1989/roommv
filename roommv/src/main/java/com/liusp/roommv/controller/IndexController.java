package com.liusp.roommv.controller;

import java.io.IOException;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liusp.roommv.common.RoommvConstant;
import com.liusp.roommv.index.HtmlIndexer;
import com.liusp.roommv.vo.AjaxResult;

@Controller(value = "indexController")
@RequestMapping("/indexControl")
public class IndexController {
	public static final Logger logger = Logger.getLogger(IndexController.class);

	@RequestMapping("/deleteAllHtmlIndex")
	public @ResponseBody
	AjaxResult deleteAllHtmlIndex() {
		AjaxResult result = new AjaxResult();
		try {
			HtmlIndexer indexer = new HtmlIndexer();
			indexer.setIndexesPath(RoommvConstant.HTML_INDEXES_PATH);
			indexer.deleteAll();
			result.setResultCode(AjaxResult.ResultCode.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setResultCode(AjaxResult.ResultCode.FAILED);
			logger.error("系统错误", e);
		}
		return result;
	}

	@RequestMapping("/createHtmlIndex")
	public @ResponseBody
	AjaxResult createHtmlIndex() {
		AjaxResult result = new AjaxResult();
		try {
			Analyzer analyzer = new PaodingAnalyzer();
			HtmlIndexer indexer = new HtmlIndexer(
					RoommvConstant.HTML_INDEXES_PATH,
					RoommvConstant.HTML_FILES_PATH, OpenMode.CREATE);
			indexer.setAnalyzer(analyzer);
			indexer.createSearchIndexes();
			result.setResultCode(AjaxResult.ResultCode.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setResultCode(AjaxResult.ResultCode.FAILED);
			logger.error("系统错误", e);
		}
		return result;
	}

	@RequestMapping("/optimizeHtmlIndex")
	public @ResponseBody
	AjaxResult optimizeHtmlIndex() {
		AjaxResult result = new AjaxResult();
		try {
			HtmlIndexer indexer = new HtmlIndexer();
			indexer.setIndexesPath(RoommvConstant.HTML_INDEXES_PATH);
			indexer.optimize();
			result.setResultCode(AjaxResult.ResultCode.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setResultCode(AjaxResult.ResultCode.FAILED);
			logger.error("系统错误", e);
		}
		return result;
	}

	@RequestMapping("")
	public String indexControl() throws IOException {
		return "indexControl";
	}
}
