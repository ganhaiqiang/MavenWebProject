package com.demo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Test {
	public static void main(String[] args) {
		HttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet();
		try {
			get.setURI(new URI("http://op.juhe.cn/onebox/weather/query?cityname=深圳&key=05eaf356f8c8c6b6a7d860deb3750f19"));
			HttpResponse response = httpClient.execute(get);
			InputStream in = response.getEntity().getContent();
			String result = IOUtils.toString(in, "UTF-8");
			JSONObject obj = JSON.parseObject(result);
			System.out.println(JSON.toJSONString(obj, true));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
