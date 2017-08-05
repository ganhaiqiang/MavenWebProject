package com.demo.async;

public class ScheduledExecutorExample implements Runnable {

	@Override
	public void run() {
		System.out.println("我是Spring ScheduledExecutorTask的任务");
	}
	
}
