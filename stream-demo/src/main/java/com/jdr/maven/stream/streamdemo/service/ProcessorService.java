package com.jdr.maven.stream.streamdemo.service;

import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @author zhoude
 * @date 2019/10/21 22:11
 */
@Service
@AllArgsConstructor
public class ProcessorService {

    private final Processor processor;

    public boolean send(String message) {
        return processor.output().send(MessageBuilder.withPayload(message).build());
    }

    public boolean subscribe(MessageHandler handler) {
        return processor.input().subscribe(handler);
    }
}
