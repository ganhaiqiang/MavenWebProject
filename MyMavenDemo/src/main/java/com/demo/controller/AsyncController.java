package com.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.async.TaskExecutorExample;

@Controller
@RequestMapping(value = "/async")
public class AsyncController {
	private static final Logger LOGGER = Logger.getLogger(AsyncController.class);
//	@Autowired
//	private TaskExecutorExample taskExecutorExample;
//	@Autowired
//	@Qualifier("threadPool")
//	private TaskExecutor taskExecutor;

	@RequestMapping(value = "/test")
	@ResponseBody
	public Object showCommandResult() {
		// taskExecutorExample.printMessages();
//		taskExecutor.execute(new Runnable() {
//
//			@Override
//			public void run() {
//				for (int i = 0; i < 50; i++) {
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					System.out.println("异步任务===>" + i);
//				}
//			}
//		});
		return "OK";
	}

}
