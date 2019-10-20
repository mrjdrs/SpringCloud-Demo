package com.jdr.designpatterns.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者模式-被观察者
 *
 * @author zhoude
 * @date 2019/10/20 22:34
 */
public class ObserverDemo extends Observable {

    /**
     * 通知观察者被观察者已发生变化
     */
    public void notifyObserver() {
        // 设置变化状态为true >>> 被观察者已发生变化
        this.setChanged();
        // 将这一变化通知观察者
        this.notifyObservers();
    }

    public static void main(String[] args) {
        testAddObserver();
        testDeleteObserver();
    }

    private static void testAddObserver() {
        ObserverDemo observerDemo = new ObserverDemo();
        observerDemo.addObserver((o, arg) -> System.err.println("服务列表发生变化1"));
        observerDemo.notifyObserver();
    }

    private static void testDeleteObserver() {
        ObserverDemo observerDemo = new ObserverDemo();
        Observer observer = (o, arg) -> System.err.println("服务列表发生变化2");

        observerDemo.addObserver(observer);
        observerDemo.notifyObserver();
        observerDemo.deleteObserver(observer);
        observerDemo.notifyObserver();
    }
}
