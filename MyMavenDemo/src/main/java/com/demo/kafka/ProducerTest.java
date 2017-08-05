package com.demo.kafka;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerTest {
	public static void main(String[] args) {
		producer();
	}

	public static void producer() {
		Map<String, Object> propsMap = new HashMap<String, Object>();
		propsMap.put("bootstrap.servers", "127.0.0.1:9092");
		propsMap.put("acks", "all");
		propsMap.put("retries", 0);
		propsMap.put("batch.size", 16384);
		propsMap.put("linger.ms", 1);
		propsMap.put("buffer.memory", 33554432);
		propsMap.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		propsMap.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		// Properties props = new Properties();
		// props.put("bootstrap.servers", "localhost:9092");
		// props.put("acks", "all");
		// props.put("retries", 0);
		// props.put("batch.size", 16384);
		// props.put("linger.ms", 1);
		// props.put("buffer.memory", 33554432);
		// props.put("key.serializer",
		// "org.apache.kafka.common.serialization.StringSerializer");
		// props.put("value.serializer",
		// "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(propsMap);
		for (int i = 0; i < 1000; i++) {
			producer.send(new ProducerRecord<String, String>("mytopic", Integer.toString(i), Integer.toString(i)));
		}
		producer.close();
	}

	public static void consumer() {
		Map<String, Object> propMap = new HashMap<String, Object>();
		propMap.put("bootstrap.servers", "localhost:9092");
		propMap.put("group.id", "0");
		propMap.put("enable.auto.commit", "true");
		propMap.put("auto.commit.interval.ms", "1000");
		propMap.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		propMap.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		// Properties props = new Properties();
		// props.put("bootstrap.servers", "localhost:9092");
		// props.put("group.id", "0");
		// props.put("enable.auto.commit", "true");
		// props.put("auto.commit.interval.ms", "1000");
		// props.put("key.deserializer",
		// "org.apache.kafka.common.serialization.StringDeserializer");
		// props.put("value.deserializer",
		// "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(propMap);
		consumer.subscribe(Arrays.asList("mytopic"));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
			}
		}
	}
}
