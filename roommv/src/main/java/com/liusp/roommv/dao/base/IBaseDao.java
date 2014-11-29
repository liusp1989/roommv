package com.liusp.roommv.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.liusp.roommv.vo.Page;

public interface IBaseDao<T, PK extends Serializable> {
	public List<Object> queryAllObjectList(Class className,
			String... filedNames);

	public List<Object> queryObjectListByPage(Class className, Page page,
			String... filedNames);

	public void addObject(Object object);

	public void deleteObject(Object object);

	public void updateObject(Object object);

	public void saveOrUpdateObject(Object object);

	public List<Object> findObjects(DetachedCriteria criteria);

	public Object findObjectById(String id);
}
