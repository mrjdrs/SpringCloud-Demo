package com.jdr.maven.event.springevent.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhoude
 * @date 2019/10/23 9:47
 */
public class SpringEventDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.addApplicationListener(event -> System.err.println("监听到事件" + event.getSource()));

        context.refresh();
        context.publishEvent(new MyApplicationEvent("event1"));
        context.publishEvent(new MyApplicationEvent("event2"));
    }
}

class MyApplicationEvent extends ApplicationEvent {

    public MyApplicationEvent(Object source) {
        super(source);
    }
}
