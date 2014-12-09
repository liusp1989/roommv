package com.liusp.roommv.dao.htmlInfo;

import java.util.List;
import java.util.Map;

import com.liusp.roommv.annotation.MybatisMapper;
import com.liusp.roommv.entity.html.HtmlInfo;

@MybatisMapper
public interface IHtmlInfoDao {

	List<HtmlInfo> queryHtmlInfos(Map<String, Object> criteria);

	void addHtmlInfo(HtmlInfo htmlInfo);

	void updateHtmlInfo(HtmlInfo htmlInfo);

	void deleteHtmlInfo(HtmlInfo htmlInfo);

	HtmlInfo queryHtmlInfoById(String id);

}
