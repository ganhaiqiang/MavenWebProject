package com.demo.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.demo.pojo.User;

public interface IHessianService {
	Map<String, Object> getMap();
	
	JSONObject getJSONObject();
	
	List<User> getUsers();
}
