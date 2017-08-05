package com.demo.listener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ContextFileListener implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger LOGGER = Logger.getLogger(ContextFileListener.class);
	@Autowired
	private FileMonitor fileMonitor;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				fileMonitor.monitor();
				try {
					fileMonitor.start();
				} catch (Exception e) {
					LOGGER.error(e);
				}
			}
		}, "monitorThread").start();
	}

}
