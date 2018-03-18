package com.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.vo.LogAppender;

public final class HttpUtil {
	private static final Logger LOG = LoggerFactory.getLogger(LogAppender.HTTP);
	private static final String CHARSET = "UTF-8";

	/**
	 * POST请求
	 * 
	 * @param url
	 *            请求地址
	 * @param content
	 *            参数 key1=val1&key2=val2&key3=val3
	 * @return
	 */
	public static String sendByPost(String url, String content) {
		StringBuilder result = new StringBuilder();
		String line = null;
		try {
			URL u = new URL(url);
			HttpURLConnection con = (HttpURLConnection) u.openConnection();

			con.setDoInput(true);
			con.setDoOutput(true);
			con.setAllowUserInteraction(false);
			con.setUseCaches(false);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			con.setConnectTimeout(10000);
			con.setReadTimeout(20000);
			OutputStream out = con.getOutputStream();
			out.write(content.getBytes(CHARSET));
			out.flush();

			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), CHARSET));
			while (null != (line = reader.readLine())) {
				result.append(line);
			}
			reader.close();
			out.close();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new HttpException(e);
		}
		return result.toString();
	}

	/**
	 * GET请求
	 * 
	 * @param url
	 *            请求地址
	 * @param content
	 *            参数 key1=val1&key2=val2&key3=val3
	 * @return
	 */
	public static String sendByGet(String url, String content) {
		StringBuilder result = new StringBuilder();
		try {
			URL u = new URL(url + "?" + content);
			HttpURLConnection con = (HttpURLConnection) u.openConnection();
			con.setUseCaches(false);
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), CHARSET));
			String line = null;
			while (null != (line = reader.readLine())) {
				result.append(line);
			}
			reader.close();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			throw new HttpException(e);
		}
		return result.toString();
	}

	/**
	 * 拼接请求参数，如：key1=val1&key2=val2&key3=val3
	 * 
	 * @param map
	 * @return
	 */
	public static String splice(Map<String, Object> map) {
		if (map == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Entry<String, Object> entry : map.entrySet()) {
			sb.append(entry.getKey()).append('=').append(entry.getValue()).append('&');
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * 
	 * @param map
	 *            将map里面的key按字典排序，然后将key=value用&连接成一个字符串
	 * @param excludeClomns
	 *            过滤不需要签名字段
	 * @return
	 */
	public static String map2AsciiStr(Map<String, Object> map, String... excludeClomns) {
		if (map == null || map.size() == 0) {
			return null;
		}
		String[] keys = {};
		keys = map.keySet().toArray(keys);
		Arrays.sort(keys);// 对字符串数组按字典排序
		StringBuilder sb = new StringBuilder();
		List<String> excludeClomnsList = Arrays.asList(excludeClomns);
		for (String str : keys) {
			// 过滤不需要签名字段
			if (excludeClomnsList.contains(str)) {
				continue;
			}

			if (map.get(str) != null && !"".equals(map.get(str))) {
				sb.append(str).append('=').append(map.get(str)).append('&');
			}
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

	public static void main(String args[]) {
		String reString = sendByGet("http://www.baidu.com", "");
		System.out.println(reString);
	}
}
