package com.liusp.roommv.service.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.liusp.roommv.vo.Page;

public interface IBaseService<T, PK extends Serializable> {
	public List<Object> getAllObjectList(Class className, String... filedNames);

	public List<Object> getObjectListByPage(Class className, Page page,
			String... filedNames);

	public void addObject(Object object);

	public void deleteObject(Object object);

	public void updateObject(Object object);

	public List<Object> findObjects(DetachedCriteria criteria);

	public Object findObjectById(String id);
}
