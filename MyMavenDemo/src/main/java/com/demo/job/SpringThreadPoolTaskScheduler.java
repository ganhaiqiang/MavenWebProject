package com.demo.job;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.demo.vo.LogAppender;

import redis.clients.jedis.Jedis;

@Component
public class SpringThreadPoolTaskScheduler implements Runnable {
	private static final Logger LOG = Logger.getLogger(LogAppender.COMMON);
	private ThreadPoolTaskScheduler scheduler;

	private String cronExpression = "0/30 * * * * ?";// 每10秒执行一次

	private static final Jedis JEDIS = new Jedis("127.0.0.1", 6379);

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
		JEDIS.auth("admin.123");
		Map<String, String> map = JEDIS.hgetAll("urls");
		LOG.info("SpringThreadPoolTaskScheduler定时任务执行！");
		LOG.info(JSON.toJSONString(map, true));
	}

}
