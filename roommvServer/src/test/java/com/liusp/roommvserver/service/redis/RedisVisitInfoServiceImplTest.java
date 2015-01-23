package com.liusp.roommvserver.service.redis;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.liusp.roommvserver.base.BaseTest;

public class RedisVisitInfoServiceImplTest extends BaseTest {
	@Resource
	private RedisVisitInfoService redisVisitInfoService;
	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void testAddVisitInfoString() {
		final String message = "test";
		Long start = System.nanoTime();
		stringRedisTemplate.executePipelined(new RedisCallback() {

			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				// TODO Auto-generated method stub
				connection.openPipeline();
				for (int i = 0; i < 100000; i++) {
					stringRedisTemplate.opsForValue().set("test:" + i, message);
				}
				return connection.closePipeline();
			}
		});
		System.out.println(System.nanoTime() - start);
	}
}
