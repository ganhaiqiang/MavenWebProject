package com.demo.disruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class Test {
	public static void main(String[] args) {
		EventFactory<LogEvent> eventFactory = new LogEventFactory();
		ExecutorService executor = Executors.newSingleThreadExecutor();
		int ringBufferSize = 1024 * 1024; // RingBuffer 大小，必须是 2 的 N 次方；

		ThreadFactory threadFactory = new ThreadPoolTaskExecutor();
		Disruptor<LogEvent> disruptor = new Disruptor<>(eventFactory, ringBufferSize, threadFactory, ProducerType.SINGLE,
				new YieldingWaitStrategy());

		EventHandler<LogEvent> eventHandler = new LogEventHandler();
		disruptor.handleEventsWith(eventHandler);

		disruptor.start();

		// 发布事件；
		RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();
		long sequence = ringBuffer.next();// 请求下一个事件序号；

		try {
			LogEvent event = ringBuffer.get(sequence);// 获取该序号对应的事件对象；
			event.setId(1000);
			event.setMsg("程序异常");
			event.setName("AppController");
		} finally {
			ringBuffer.publish(sequence);// 发布事件；
		}

		disruptor.shutdown();// 关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
		executor.shutdown();// 关闭disruptor使用的线程池；如果需要的话，必须手动关闭，disruptor在shutdown时不会自动关闭；
	}
}
