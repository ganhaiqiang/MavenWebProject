package com.demo.test;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class Test6 {
	public static void main(String[] args) {
		System.out.println(DigestUtils.md5Hex("123456"));
	}
}
