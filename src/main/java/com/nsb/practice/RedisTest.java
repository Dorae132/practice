package com.nsb.practice;

import java.util.List;

import com.google.common.collect.Lists;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class RedisTest {
	private static final String JOB_LUA = "if tonumber(redis.call('HGET', KEYS[1], 'updatetime')) "
			+ " < tonumber(ARGV[1]) "
			+ "then "
			+ "redis.call('HDEL', KEYS[1], 'updatetime') "
			+ "redis.call('SREM', KEYS[2], ARGV[2]) "
			+ "return '1' "
			+ "else "
			+ "return '0' "
			+ "end ";
	
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		Pipeline pipeline = jedis.pipelined();
		for(int i = 0; i < 10; i++) {
			pipeline.eval(JOB_LUA,
					Lists.newArrayList("userId#roomId",
							"setkey"),
					Lists.newArrayList("2", "userId#roomId"));
//			pipeline.set(i + "#", i + "");
		}
//		pipeline.syncAndReturnAll();
		List<Object> syncAndReturnAll = pipeline.syncAndReturnAll();
		for (Object object : syncAndReturnAll) {
			System.out.println(Integer.valueOf(object.toString()));
		}
	}
}
