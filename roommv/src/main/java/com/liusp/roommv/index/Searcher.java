package com.liusp.roommv.index;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.htmlparser.util.ParserException;

import com.liusp.roommv.vo.Html;
import com.liusp.roommv.vo.Page;

/**
 * 
 * @author jackyliu 创建文件索引
 */
public interface Searcher {
	public void setAnalyzer(Analyzer analyzer);


	public List<Html> searchInfoByPage(String[] fields, String info, Page page)
			throws IOException, ParserException, ParseException,
			InvalidTokenOffsetsException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException;
}
