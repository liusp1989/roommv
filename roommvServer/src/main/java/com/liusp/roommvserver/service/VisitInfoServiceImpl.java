package com.liusp.roommvserver.service;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.liusp.roommvserver.common.Constants;
import com.liusp.roommvserver.dao.VisitInfoDao;
import com.liusp.roommvserver.entity.VisitInfo;

@Service
public class VisitInfoServiceImpl implements VisitInfoService {
	@Resource
	private VisitInfoDao visitInfoDao;
	@Override
	public void addVisitInfo(VisitInfo visitInfo) {
		// TODO Auto-generated method stub
		visitInfoDao.addVisitInfo(visitInfo);
	}

	public void addVisitInfo(String jsonMessage) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject(jsonMessage);
		if (!Constants.SynBizType.VISITINFO.getBizType().equals(
				jsonObject.getString("bizType"))) {
			return;
		}
		try {
			String visitInfoStr = String.valueOf(jsonObject.get("visitInfo"));
			JSONObject visitInfObject = new JSONObject(visitInfoStr);
			VisitInfo visitInfo = new VisitInfo();
			if (visitInfObject.has("createDate")) {
				visitInfo.setCreateDate(visitInfObject.getString("createDate"));
			}
			if (visitInfObject.has("currentUrl")) {
				visitInfo.setCurrentUrl(visitInfObject.getString("currentUrl"));
			}
			if (visitInfObject.has("ip")) {
				visitInfo.setIp(visitInfObject.getString("ip"));
			}
			if (visitInfObject.has("referUrl")) {
				visitInfo.setReferUrl(visitInfObject.getString("referUrl"));
			}
			if (visitInfObject.has("userId")) {
				visitInfo.setUserId(visitInfObject.getString(""));
			}
			this.addVisitInfo(visitInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
