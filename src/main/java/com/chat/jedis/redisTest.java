package com.chat.jedis;

import java.util.List;

import javax.websocket.server.ServerEndpoint;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@ServerEndpoint("/redisTest")
public class redisTest {
	
	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	public void  envtest() {
		String key = "testkey";
		String redispassword = "lenpass";
		int redisdatabase = 70;
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.auth(redispassword);
		jedis.select(redisdatabase);
		jedis.set(key, "0");
		System.out.println("set " + key +" as 0 ");
		jedis.incr(key);
		System.out.println("increase \" + key +\" from 0 ");
		jedis.close();
	}	
	
}
