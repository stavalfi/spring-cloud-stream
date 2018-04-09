package com.nice.spring.cloud.stream.errors.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.SubscribableChannel;

@EnableBinding(SinkError.class)
@SpringBootApplication
public class SpringCloudStreamErrorsConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamErrorsConsumerApplication.class, args);
	}

	@StreamListener(SinkError.INPUT) // destination name 'input.myGroup'
	public void handle(Message<?> message) {
		System.out.println("Handling ERROR: " + message);
	}
}

interface SinkError {
	String INPUT = "myErrors";

	@Input("myErrors")
	SubscribableChannel myErrors();
}
