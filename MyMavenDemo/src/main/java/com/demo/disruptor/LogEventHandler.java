package com.demo.disruptor;

import com.alibaba.fastjson.JSON;
import com.lmax.disruptor.EventHandler;

public class LogEventHandler implements EventHandler<LogEvent> {

	@Override
	public void onEvent(LogEvent arg0, long arg1, boolean arg2) throws Exception {
		Thread.sleep(1000);
		System.out.println(JSON.toJSONString(arg0));
	}

}
