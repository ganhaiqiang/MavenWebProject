package com.demo.test;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

public class Test1 implements MessageListener<String, String> {
	public static void main(String[] args) {

	}

	@Override
	public void onMessage(ConsumerRecord<String, String> arg0) {
		// TODO Auto-generated method stub

	}
}
