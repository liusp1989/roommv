package com.liusp.roommvserver.service.redis;

import redis.clients.jedis.ShardedJedis;


public interface BaseRedisService {

	public ShardedJedis getShardedJedis();

	public void returnBrokenResource(ShardedJedis shardedJedis);

	public void returnResource(ShardedJedis shardedJedis);

	public Long getAutoIncrId(String name, Long step);

	public String getValue(String key);

}
