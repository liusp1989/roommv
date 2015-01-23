package com.liusp.roommvserver.service.redis;

import redis.clients.jedis.Jedis;


public interface BaseRedisService {

	public Jedis getJedisClient();

	public void returnBrokenResource(Jedis jedis);

	public void returnResource(Jedis jedis);

	public Long getAutoIncrId(String name, Long step);
}
