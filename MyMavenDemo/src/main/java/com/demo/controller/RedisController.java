package com.demo.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/redis")
public class RedisController {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@RequestMapping(value = "/getKeys", method = RequestMethod.GET)
	@ResponseBody
	public Object getKeys(String pattern) {
		Set<String> keys = redisTemplate.keys("com.demo.service.StudentService.*");
		System.out.println(JSON.toJSONString(keys, true));
		return keys;
	}
	
	@RequestMapping(value = "/clearKeys", method = RequestMethod.GET)
	@ResponseBody
	public Object clearKeys(String pattern) {
		Set<String> keys = redisTemplate.keys("com.demo.service.StudentService.*");
		redisTemplate.delete(keys);
		return "ok";
	}
}
