package com.demo.test;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.SpringProperties;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class Test8 {
	public static void main(String[] args) {
		try {
			Properties properties = PropertiesLoaderUtils.loadAllProperties("config.xml");
			System.out.println(properties.get("email"));
			System.out.println(SpringProperties.getProperty("hessian_server"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
