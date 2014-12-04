package com.liusp.roommv.service.htmlInfo;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.liusp.roommv.dao.htmlInfo.IHtmlInfoDao;
import com.liusp.roommv.entity.html.HtmlInfo;
import com.liusp.roommv.service.base.BaseServiceImpl;

@Service(value = "htmlInfoService")
public class HtmlInfoServiceImpl extends BaseServiceImpl<HtmlInfo, String>
		implements IHtmlInfoService<HtmlInfo, String> {
	private IHtmlInfoDao htmlInfoDao;
	@Resource(name = "htmlInfoDao")
	public void setBaseDao(IHtmlInfoDao htmlInfoDao) {
		this.htmlInfoDao = htmlInfoDao;
		super.setBaseDao(htmlInfoDao);
	}

	@Override
	public void saveOrupdateObject(HtmlInfo htmlInfo) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(HtmlInfo.class);
		Criterion criterion = Restrictions.eq("id", htmlInfo.getId());
		criteria.add(criterion);
		List<HtmlInfo> htmlInfos = htmlInfoDao.findObjects(criteria);
		if (htmlInfos.isEmpty()) {
			htmlInfoDao.addObject(htmlInfo);
		} else {
			htmlInfoDao.updateObject(htmlInfo);
		}
	}
}
