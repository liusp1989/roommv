package com.liusp.roommvserver.service.redis;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.liusp.roommvserver.common.Constants;

@Service
public class BaseRedisServiceImpl implements BaseRedisService {
	private static final Logger logger = Logger
			.getLogger(BaseRedisServiceImpl.class);
	// @Resource
	// private StringRedisTemplate stringRedisTemplate;
	@Resource
	private JedisPool jedisPool;

	public Jedis getJedisClient() {
		try {
			Jedis jedis = jedisPool.getResource();
			if (jedis != null) {
				return jedis;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("获取缓存失败", e);
		}
		return null;

	}

	public void returnBrokenResource(Jedis jedis) {
		jedisPool.returnBrokenResource(jedis);
	};

	public void returnResource(Jedis jedis) {
		jedisPool.returnResource(jedis);
	};
	@Override
	public Long getAutoIncrId(String name, Long step) {
		// TODO Auto-generated method stu
		if (StringUtils.isEmpty(name)) {
			throw new RuntimeException("自增主键不能为空");
		}
		if (step == null) {
			throw new RuntimeException("自增值不能为空");
		}
		Long autoId = null;
		Jedis jedis = null;
		try {
			jedis = this.getJedisClient();
			autoId = jedis.incrBy(Constants.REDIS_ID_TABLE
					+ Constants.REDIS_KEY_SEPARATOR + name, step);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			this.returnResource(jedis);
		}
		return autoId == null ? 0 : autoId;
	}

}
