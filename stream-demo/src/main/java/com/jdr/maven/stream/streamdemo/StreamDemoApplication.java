package com.jdr.maven.stream.streamdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;

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

//    /**
//     * RabbitMQ不支持消息分流
//     */
//    @StreamListener(value = Sink.INPUT, condition = "headers['contentType']=='mgs1'")
//    public void receiveMessage1(@Payload Message<String> message) {
//        System.err.println("receive message1: " + message.getPayload());
//    }
//
//    /**
//     * RabbitMQ不支持消息分流
//     */
//    @StreamListener(value = Sink.INPUT, condition = "headers['contentType']=='mgs2'")
//    public void receiveMessage2(@Payload Message<String> message) {
//        System.err.println("receive message2: " + message.getPayload());
//    }
}
