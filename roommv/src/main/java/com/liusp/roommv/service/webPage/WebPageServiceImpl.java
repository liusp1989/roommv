package com.liusp.roommv.service.webPage;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liusp.roommv.dao.webPage.IWebPageDao;
import com.liusp.roommv.entity.webPage.WebPage;
import com.liusp.roommv.service.base.BaseServiceImpl;

@Service("webPageService")
public class WebPageServiceImpl extends BaseServiceImpl<WebPage, String>
		implements
		IWebPageService<WebPage, String> {
	@Resource(name = "webPageDao")
	public void setBaseDao(IWebPageDao webPageDao) {
		super.setBaseDao(webPageDao);
	}
}
