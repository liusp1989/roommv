package com.liusp.roommv.controller;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import com.liusp.roommv.base.BaseTest;

public class RedisTest extends BaseTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// super.tearDownAfterClass();
	}

	@Resource(name = "redisTemplate")
	private RedisTemplate redisTemplate;
	@Test
	public void test() {

		try {
			// redis.opsForValue().set("aa", "bb");
			System.out.println(redisTemplate.opsForValue().get("aa"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public static void main(String[] args) {
	// Jedis jedis = new Jedis("192.168.223.130", 6479, 10000);
	// // jedis.set("aa", "bb");
	// System.out.println(jedis.get("aa"));
	//
	// }
}
