package com.liusp.roommv.dao.base;

import java.util.List;

import com.liusp.roommv.vo.Page;

public interface IBaseDao {
	public List<Object> queryAllObjectList(Class className,
			String... filedNames);

	public List<Object> queryObjectListByPage(Class className, Page page,
			String... filedNames);
}
