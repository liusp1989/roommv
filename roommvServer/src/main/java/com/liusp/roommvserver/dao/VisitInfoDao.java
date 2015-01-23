package com.liusp.roommvserver.dao;

import com.liusp.roommvserver.annotation.MybatisMapper;
import com.liusp.roommvserver.entity.VisitInfo;

@MybatisMapper
public interface VisitInfoDao {
	public void addVisitInfo(VisitInfo visitInfo);
}
