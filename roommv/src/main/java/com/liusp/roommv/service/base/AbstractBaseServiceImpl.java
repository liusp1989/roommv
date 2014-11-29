package com.liusp.roommv.service.base;

import java.util.List;

import javax.annotation.Resource;

import com.liusp.roommv.dao.base.IBaseDao;
import com.liusp.roommv.vo.Page;

public abstract class AbstractBaseServiceImpl implements IBaseService {

	@Resource(name = "baseDao")
	private IBaseDao baseDao;

	public List<Object> getAllObjectList(Class className, String... filedNames) {
		// TODO Auto-generated method stub
		return baseDao.queryAllObjectList(className, filedNames);
	}

	public List<Object> getObjectListByPage(Class className, Page page,
			String... filedNames) {
		// TODO Auto-generated method stub
		return baseDao.queryObjectListByPage(className, page, filedNames);
	}


}
