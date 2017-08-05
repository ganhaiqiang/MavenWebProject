package com.demo.utils;

import java.util.HashMap;
import java.util.Map;

public class UrlMappingUtils {
	private static final Map<String, Object> URL_MAP = new HashMap<>();

	public static void initKeyValue(String key, Object value) {
		URL_MAP.put(key, value);
	}

	public static Map<String, Object> getUrlMap() {
		return URL_MAP;
	}
}
