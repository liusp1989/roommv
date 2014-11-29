package com.liusp.roommv.service.base;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.liusp.roommv.dao.base.IBaseDao;
import com.liusp.roommv.vo.Page;

@Service(value = "baseServiceImpl")
public class BaseServiceImpl<T, PK extends Serializable>
		implements IBaseService<T, PK> {
	@Resource(name = "baseDaoImpl")
	private IBaseDao baseDao;

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Object> getAllObjectList(Class className, String... filedNames) {
		// TODO Auto-generated method stub
		return baseDao.queryAllObjectList(className, filedNames);
	}

	public List<Object> getObjectListByPage(Class className, Page page,
			String... filedNames) {
		// TODO Auto-generated method stub
		return baseDao.queryObjectListByPage(className, page, filedNames);
	}

	public void addObject(Object object) {
		baseDao.addObject(object);
	}

	public void deleteObject(Object object) {
		baseDao.deleteObject(object);
	}

	public void updateObject(Object object) {
		baseDao.updateObject(object);
	}

	public List<Object> findObjects(DetachedCriteria criteria) {
		return baseDao.findObjects(criteria);
	}

	public Object findObjectById(String id) {
		return baseDao.findObjectById(id);
	}

}
