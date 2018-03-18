package com.demo.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {
	private static Properties prop;

	private static Logger log = LoggerFactory.getLogger(PropertiesUtil.class);

	private static Long lastModified = 0L;

	private static void init() {
		prop = new Properties();
		// String filepath =
		// PropertiesUtil.class.getClassLoader().getResource("jdbc.properties").getPath();
		// log.info(filepath);
		// FileInputStream fis = null;
		// try {
		// fis = new FileInputStream(filepath);
		// prop.load(fis);
		// } catch (IOException e) {
		// log.error("载入系统路径资源文件错误!");
		// e.printStackTrace();
		// }
		InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {

		if (prop == null || isPropertiesModified()) {
			if (isPropertiesModified()) {
				System.out.println("*****文件改了******");
			}
			init();
		}
		String value = prop.get(key).toString();
		return value;
	}

	// 判断是否被修改过
	public static boolean isPropertiesModified() {
		boolean returnValue = false;
		File file = new File(PropertiesUtil.class.getClassLoader().getResource("jdbc.properties").getPath());
		if (file.lastModified() > lastModified) {
			lastModified = file.lastModified();
			returnValue = true;
		}
		return returnValue;
	}

	public static void main(String[] args) {
		// File file = new
		// File(PropertiesUtil.class.getClassLoader().getResource("/resource/jdbc.properties").getPath());
		// System.out.println(PropertiesUtil.class.getClassLoader().getResource("jdbc.properties").getPath());

		System.out.println(PropertiesUtil.getProperty("jdbc.url"));
	}
}
