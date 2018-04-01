package com.nice.spring.cloud.stream.producer;

// **********************************
// Producer
// **********************************


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.reactive.StreamEmitter;
import reactor.core.publisher.Flux;

import java.time.Duration;

@EnableBinding(Source.class)
@SpringBootApplication
public class SpringCloudStreamKinesisProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamKinesisProducerApplication.class, args);
	}

	@StreamEmitter
	@Output(Source.OUTPUT)
	public Flux<String> emit() {
		return Flux.interval(Duration.ofMillis(1000))
				.map(i -> i + "")
				.doOnNext(System.out::println);
	}
}