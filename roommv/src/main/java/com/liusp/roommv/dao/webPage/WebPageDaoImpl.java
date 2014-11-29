package com.liusp.roommv.dao.webPage;

import org.springframework.stereotype.Service;

import com.liusp.roommv.dao.base.BaseDaoImpl;
import com.liusp.roommv.entity.webPage.WebPage;

@Service(value = "webPageDao")
public class WebPageDaoImpl extends BaseDaoImpl<WebPage, String>
		implements IWebPageDao<WebPage, String> {

}
