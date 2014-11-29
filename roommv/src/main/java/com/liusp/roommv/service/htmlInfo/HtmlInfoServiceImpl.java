package com.liusp.roommv.service.htmlInfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liusp.roommv.dao.htmlInfo.IHtmlInfoDao;
import com.liusp.roommv.entity.html.HtmlInfo;
import com.liusp.roommv.service.base.BaseServiceImpl;

@Service(value = "htmlInfoService")
public class HtmlInfoServiceImpl extends BaseServiceImpl<HtmlInfo, String>
		implements IHtmlInfoService<HtmlInfo, String> {
	@Resource(name = "htmlInfoDao")
	public void setBaseDao(IHtmlInfoDao htmlInfoDao) {
		super.setBaseDao(htmlInfoDao);
	}
}
