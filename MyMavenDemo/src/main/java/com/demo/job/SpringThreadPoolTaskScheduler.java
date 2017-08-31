package com.demo.job;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
public class SpringThreadPoolTaskScheduler implements Runnable {
	private ThreadPoolTaskScheduler scheduler;

	private String cronExpression = "0/10 * * * * ?";// 每10秒执行一次

	@PostConstruct
	private void start() {
		scheduler = new ThreadPoolTaskScheduler();
		scheduler.initialize();
		CronTrigger cron = new CronTrigger(cronExpression);
		scheduler.schedule(this, cron);
	}

	@PreDestroy
	private void destroy() {
		scheduler.shutdown();
	}

	@Override
	public void run() {
		System.out.println("SpringThreadPoolTaskScheduler定时任务执行！");
	}

}
