package com.liusp.roommv.common;

public class RoommvConstant {
	public static final String WEB_ROOT = System.getProperty("webapp.root");
	public static final String HTML_FILES_PATH = WEB_ROOT
			+ System.getProperty("html.files.path");
	public static final String HTML_FILES_TEMPPATH = WEB_ROOT
			+ System.getProperty("html.files.tempPath");
	public static final String HTML_INDEXES_PATH = System
			.getProperty("html.indexes.path");
	public static final int TEXT_FRAGMENT_TIMES = Integer.valueOf(System
			.getProperty("index.textFragment.times"));// 标注查询结果高亮显示的片段出现次数
	public static final int HTML_CONTENT_LENGTH = Integer.valueOf(System
			.getProperty("html.content.length"));// 查询出html内容显示长度
	public static final String ALL_SIGN = "ALL";

	public static final String NO_MULTI_VALIDATE = "/ueditor";/*
															 * 因为使用了百度ueditor所以/
															 * ueditor请求不做spring的multi校验
															 */

}

