package com.demo.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.joda.time.LocalTime;

public class Test7 {
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(20);
		while (LocalTime.now().getMinuteOfHour() < 1) {
			service.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println("*************************");
				}
			});
		}
		service.shutdown();
	}
}
