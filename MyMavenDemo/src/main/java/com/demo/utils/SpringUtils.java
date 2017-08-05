package com.demo.utils;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

public class SpringUtils {
	private static WebApplicationContext applicationContext;
	static {
		applicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
	}

	public static Object getBean(String beanName) {
		if (applicationContext != null) {
			return applicationContext.getBean(beanName);
		}
		return null;
	}

	public static <T> T getBean(Class<T> t) {
		if (applicationContext != null) {
			return applicationContext.getBean(t);
		}
		return null;
	}
}
