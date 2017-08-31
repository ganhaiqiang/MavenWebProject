package com.demo.listener;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import redis.clients.jedis.Jedis;

public class MyHandlerMapping extends RequestMappingHandlerMapping {
	private static final Jedis JEDIS = new Jedis("127.0.0.1");

	@Override
	protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
		super.registerHandlerMethod(handler, method, mapping);
		System.out.println("==========================");
		Set<String> urls = mapping.getPatternsCondition().getPatterns();
		Map<String, String> map = new HashMap<>();
		for (String url : urls) {
			map.put(url, "0");
			System.out.println(url);
		}
		JEDIS.hmset("urls", map);
	}

}
