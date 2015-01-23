package com.liusp.roommvserver.service;

import com.liusp.roommvserver.entity.VisitInfo;

public interface VisitInfoService {
	public void addVisitInfo(VisitInfo visitInfo);

	public void addVisitInfo(String jsonMessage);
}
