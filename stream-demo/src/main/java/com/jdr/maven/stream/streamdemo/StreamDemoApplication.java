package com.jdr.maven.stream.streamdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
@EnableBinding({Processor.class})
public class StreamDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamDemoApplication.class, args);
	}

	@StreamListener(Sink.INPUT)
	public void receive(String message) {
		System.err.println("receive message: " + message);
	}
}
