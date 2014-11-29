package com.liusp.roommv.service.base;

import java.util.List;

import com.liusp.roommv.vo.Page;

public interface IBaseService {
	public List<Object> getAllObjectList(Class className, String... filedNames);

	public List<Object> getObjectListByPage(Class className, Page page,
			String... filedNames);
}
