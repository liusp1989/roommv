package com.liusp.roommvserver.service.redis;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import redis.clients.jedis.*;

import com.liusp.roommvserver.common.Constants;

@Service
public class BaseRedisServiceImpl implements BaseRedisService {
	private static final Logger logger = Logger
			.getLogger(BaseRedisServiceImpl.class);
	// @Resource
	// private StringRedisTemplate stringRedisTemplate;
	@Resource
	private ShardedJedisPool shardedJedisPool;

	public ShardedJedis getShardedJedis() {
		try {
			ShardedJedis shardedJedis = shardedJedisPool.getResource();
			if (shardedJedis != null) {
				return shardedJedis;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("获取缓存失败", e);
		}
		return null;

	}

	public void returnBrokenResource(ShardedJedis shardedJedis) {
		shardedJedisPool.returnBrokenResource(shardedJedis);
	};

	public void returnResource(ShardedJedis shardedJedis) {
		shardedJedisPool.returnResource(shardedJedis);
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
		ShardedJedis jedis = null;
		try {
			jedis = this.getShardedJedis();
			autoId = jedis.incrBy(Constants.REDIS_ID_TABLE
					+ Constants.REDIS_KEY_SEPARATOR + name, step);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.returnBrokenResource(jedis);
			e.printStackTrace();
		}
			this.returnResource(jedis);
		return autoId == null ? 0 : autoId;
	}

	@Override
	public String getValue(String key) {
		String value = null;
		ShardedJedis jedis = null;
		try {
			jedis = this.getShardedJedis();
			 value = jedis.get(key);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			this.returnBrokenResource(jedis);
			e.printStackTrace();
		}
		return value;
	}


}
