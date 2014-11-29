package com.liusp.roommv.dao.htmlInfo;

import org.springframework.stereotype.Repository;

import com.liusp.roommv.dao.base.BaseDaoImpl;
import com.liusp.roommv.entity.html.HtmlInfo;

@Repository(value = "htmlInfoDao")
public class HtmlInfoDaoImpl extends BaseDaoImpl<HtmlInfo, String> implements
		IHtmlInfoDao<HtmlInfo, String> {

}
