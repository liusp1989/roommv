package com.liusp.roommvserver.service.redis;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.data.redis.hash.DecoratingStringHashMapper;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.JacksonHashMapper;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.liusp.roommvserver.common.Constants;
import com.liusp.roommvserver.entity.VisitInfo;
import redis.clients.jedis.ShardedJedis;

@Service
public class RedisVisitInfoServiceImpl implements RedisVisitInfoService {

	@Resource
	private BaseRedisService baseRedisService;

	@Override
	public void addVisitInfo(VisitInfo visitInfo) {
		// TODO Auto-generated method stub

		Long id = baseRedisService.getAutoIncrId("visitInfo", 1l);
		visitInfo.setId(id.intValue());
		ShardedJedis jedis = null;
		try {
			jedis = baseRedisService.getShardedJedis();
			HashMapper<VisitInfo, String, String> hashMapper = new DecoratingStringHashMapper<VisitInfo>(
					new JacksonHashMapper<VisitInfo>(VisitInfo.class));
			jedis.hmset("visitInfo" + Constants.REDIS_KEY_SEPARATOR + id,
					hashMapper.toHash(visitInfo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			baseRedisService.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			baseRedisService.returnResource(jedis);
		}
	}

	public void addVisitInfo(String jsonMessage) {
		// TODO Auto-generated method stub
		try {
			JSONObject jsonObject = new JSONObject(jsonMessage);
			if (!Constants.SynBizType.VISITINFO.getBizType().equals(
					jsonObject.getString("bizType"))) {
				return;
			}
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
			System.out.println(jsonMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getVisitInfo(String key) {
	return 	baseRedisService.getValue(key);
	}
}
