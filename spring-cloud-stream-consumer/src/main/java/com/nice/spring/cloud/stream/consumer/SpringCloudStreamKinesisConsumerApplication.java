package com.nice.spring.cloud.stream.consumer;

// **********************************
// Consumer
// **********************************

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


interface MyInputChannels {
	String KINESIS_INPUT_CHANNEL = "input-channel-from-kinesis";

	@Input(KINESIS_INPUT_CHANNEL)
	SubscribableChannel kinesisInputChannel();
}

@EnableBinding(MyInputChannels.class)
@SpringBootApplication
public class SpringCloudStreamKinesisConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamKinesisConsumerApplication.class, args);
	}

	long startTime = System.currentTimeMillis();
	int counter = 0;

	@StreamListener(value = MyInputChannels.KINESIS_INPUT_CHANNEL)
	public void kinesisSubscriber(String payload) {
		String currentDate = DateTimeFormatter.ofPattern("hh:mm:ss.SSS").format(ZonedDateTime.now());
		long currentTime = System.currentTimeMillis();
		long delay = currentTime - this.startTime;
		this.startTime = System.currentTimeMillis();
		System.out.println("current time: " + currentDate + ", time from last batch: " + delay + ", messsages until now: " + counter + " - kinesis consumer received data: " + payload);
		counter++;
	}
}