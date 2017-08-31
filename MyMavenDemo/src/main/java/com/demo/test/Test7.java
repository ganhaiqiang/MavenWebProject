package com.demo.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test7 {
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(20000);
		for (int i = 0; i < 20000; i++) {
			final int index = i;
			service.submit(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(100000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("第" + index + "个任务！");
				}
			});
		}
		//service.shutdown();
	}
}
