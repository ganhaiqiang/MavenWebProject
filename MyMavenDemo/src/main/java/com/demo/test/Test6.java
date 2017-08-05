package com.demo.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class Test6 {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) ctx.getBean("threadPoolTaskExecutor");
		taskExecutor.execute(() -> {
			// try {
			// Thread.sleep(3000);
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			System.out.println("异步线程执行完毕");
		});
		System.out.println("主线程结束");
	}
}
