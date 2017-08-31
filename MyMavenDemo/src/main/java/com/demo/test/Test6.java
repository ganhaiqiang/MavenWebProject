package com.demo.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;

public class Test6 {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		Map<String, String> map = new HashMap<>();
		map.put("/add", "0");
		map.put("/update", "0");
		map.put("/query", "0");
		map.put("/delete", "0");
		jedis.hmset("urls", map);
		System.out.println(jedis.hlen("urls"));
		List<String> getValus = jedis.hmget("urls", "/query", "/add");
		System.out.println(JSON.toJSONString(getValus, true));

		System.out.println(JSON.toJSONString(jedis.hgetAll("urls"), true));
		Map<String, String> map2 = new HashMap<>();
		map2.put("/query", "1");
		Map<String, String> map3 = new HashMap<>();
		map3.put("/delete", "1");
		System.out.println(JSON.toJSONString(jedis.hmset("urls", map2), true));
		System.out.println(JSON.toJSONString(jedis.hmset("urls", map3), true));
		System.out.println(JSON.toJSONString(jedis.hgetAll("urls"), true));
		System.out.println(JSON.toJSONString(jedis.hmget("urls", "/add2"), true));

		jedis.del("urls");
	}
}
