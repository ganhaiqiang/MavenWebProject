package com.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

public class PropertieUtils {
	public static String getValue(String key) {
		String value = null;
		try {
			String path = PropertieUtils.class.getClassLoader().getResource("jdbc.properties").toURI().getPath();
			InputStream in = new FileInputStream(new File("D:/jdbc.properties"));
			Properties properties = new Properties();
			properties.load(in);
			value = properties.getProperty("jdbc.url");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static void main(String[] args) {
		System.out.println(PropertieUtils.getValue(""));
	}
}
