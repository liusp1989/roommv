package com.liusp.roommv.service.webPage;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liusp.roommv.dao.webPage.IWebPageDao;

@Service("webPageService")
public class WebPageServiceImpl implements IWebPageService {
	@Resource
	private IWebPageDao webPageDao;
}
