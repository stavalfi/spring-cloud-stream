package com.nice.spring.cloud.stream.producer;

// **********************************
// Producer
// **********************************


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.annotation.InboundChannelAdapter;

@EnableBinding(Source.class)
@SpringBootApplication
public class SpringCloudStreamKinesisProducerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(SpringCloudStreamKinesisProducerApplication.class, args);
	}

	static int x = 0;

	@InboundChannelAdapter(Source.OUTPUT)
	public String source() {
		String s = (x++) + "";
		System.out.println(s);
		return s;
	}
}