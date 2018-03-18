package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/async")
public class AsyncController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncController.class);
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
