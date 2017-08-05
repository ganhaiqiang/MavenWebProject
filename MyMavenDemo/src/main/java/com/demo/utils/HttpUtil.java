/**
 * =================================================================
 * 版权所有 2011-2013 深圳市泰海网络科技服务有限公司，并保留所有权利
 * -----------------------------------------------------------------
 * 这不是一个自由软件！您不能在任何未经允许的前提下对程序代码进行修改和使用；
 * 不允许对程序代码以任何形式任何目的的再发布
 * =================================================================
 */
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

import org.apache.http.ParseException;

/**
 * 类说明：<br>
 * 
 * 
 * <p>
 * 详细描述：<br>
 * 
 * 
 * </p>
 * 
 * @author 顺银收单开发组
 * 
 *         CreateDate: 2013-7-23
 */
public final class HttpUtil {

	private static final String CHARSET = "UTF-8";

	/**
	 * 
	 * 方法说明：<br>
	 * 
	 * @param url
	 *            请求url
	 * @param content
	 *            post参数 key1=val1&key2=val2&key3=val3
	 * @return
	 * @throws Exception
	 */
	public static String sendByPost(String url, String content) throws Exception {
		StringBuffer result = new StringBuffer();
		URL u = new URL(url);
		HttpURLConnection con = (HttpURLConnection) u.openConnection();

		con.setDoInput(true);
		con.setDoOutput(true);
		con.setAllowUserInteraction(false);
		con.setUseCaches(false);
		con.setRequestMethod("POST");
		// con.setRequestProperty("Content-Type",
		// "application/json;charset=utf-8");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		con.setConnectTimeout(10000);
		con.setReadTimeout(20000);
		OutputStream out = null;
		BufferedReader reader = null;
		String line = null;
		try {
			out = con.getOutputStream();
			out.write(content.getBytes(CHARSET));
			out.flush();

			reader = new BufferedReader(new InputStreamReader(con.getInputStream(), CHARSET));
			while (null != (line = reader.readLine())) {
				result.append(line);
			}
		} finally {
			if (out != null) {
				out.close();
			}
			if (reader != null) {
				reader.close();
			}
		}
		return result.toString();
	}

	/**
	 * 
	 * 方法说明：<br>
	 * 
	 * @param url
	 *            请求url
	 * @param content
	 *            get参数 key1=val1&key2=val2&key3=val3
	 * @return
	 * @throws IOException
	 */
	public static String sendByGet(String url, String content) throws IOException {
		URL u = new URL(url + "?" + content);
		HttpURLConnection con = (HttpURLConnection) u.openConnection();
		con.setUseCaches(false);
		StringBuffer result = new StringBuffer();
		BufferedReader reader = null;
		String line = null;
		reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "GBK"));
		while (null != (line = reader.readLine())) {
			result.append(line);
		}
		if (reader != null) {
			reader.close();
		}
		return result.toString();
	}

	/**
	 * 将map拼接成请求字符串，如：key1=val1&key2=val2&key3=val3
	 * 
	 * @param map
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String splice(Map<String, Object> map) throws ParseException, IOException {
		if (map == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for (Entry<String, Object> entry : map.entrySet()) {
			if (index != 0) {
				sb.append('&');
			}
			sb.append(entry.getKey()).append('=').append(entry.getValue());
			index++;
		}
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
				sb.append(str).append('=').append(map.get(str));
				sb.append('&');
			}
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

	public static void main(String args[]) throws Exception {
		// String url = "http://localhost:8080/MyDemo/service/param/demo6";
		String url = "http://memberapp.intsit.sfdc.com.cn:1080/api/redis/cache/v1?param=name%3A123%7Cset%3A789";
		// String content = "ype=phone&dir_guid=0";
		System.out.println(HttpUtil.sendByPost(url, ""));// "{\"dts\":[\"2016-09\",\"2016-10\"],\"info\":{\"custCode\":\"7550335485\",\"deptCode\":\"755S\"},\"name\":\"甘海强\"}"));
	}
}
