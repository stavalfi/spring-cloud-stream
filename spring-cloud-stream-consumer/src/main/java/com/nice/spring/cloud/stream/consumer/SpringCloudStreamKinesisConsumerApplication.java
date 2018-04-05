package com.nice.spring.cloud.stream.consumer;

// **********************************
// Consumer
// **********************************

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;


@EnableBinding(Sink.class)
@SpringBootApplication
public class SpringCloudStreamKinesisConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamKinesisConsumerApplication.class, args);
	}

	@StreamListener(Sink.INPUT) // destination name 'input.myGroup'
	public void handle(String value) {
		throw new RuntimeException("BOOM!");
	}

	@ServiceActivator(inputChannel = "stava2.myGroup.errors")
	public void error(Message<?> message) {
		System.out.println("Handling ERROR: " + message);
	}

	@StreamListener("errorChannel")
	public void errorGlobal(Message<?> message) {
		System.out.println("Handling ERROR: " + message);
	}
}