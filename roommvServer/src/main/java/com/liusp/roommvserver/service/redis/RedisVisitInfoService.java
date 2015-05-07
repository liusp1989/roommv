package com.liusp.roommvserver.service.redis;

import com.liusp.roommvserver.entity.VisitInfo;

public interface RedisVisitInfoService {
	public void addVisitInfo(VisitInfo visitInfo);

	public void addVisitInfo(String jsonMessage);

	public String getVisitInfo(String key);

}
