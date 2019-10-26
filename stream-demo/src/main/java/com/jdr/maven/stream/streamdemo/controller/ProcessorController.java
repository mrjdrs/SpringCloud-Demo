package com.jdr.maven.stream.streamdemo.controller;

import com.jdr.maven.stream.streamdemo.service.ProcessorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoude
 * @date 2019/10/21 22:10
 */
@RestController
@AllArgsConstructor
public class ProcessorController {

    private final ProcessorService processorService;

    @GetMapping("/testProcessor/{message}")
    public boolean testProcessor(@PathVariable("message") String message) {
        return processorService.send(message);
    }

//    /**
//     * RabbitMQ不支持消息分流
//     */
//    @GetMapping("/testMessageShunt/{type}")
//    public boolean testMessageShunt(@PathVariable("type") String type) {
//        String header = "a".equalsIgnoreCase(type) ? "msg1" : "msg2";
//        return processorService.send(type, header);
//    }
}
