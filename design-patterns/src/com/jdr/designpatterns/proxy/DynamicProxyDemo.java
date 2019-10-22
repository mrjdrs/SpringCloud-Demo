package com.jdr.designpatterns.proxy;

import com.jdr.designpatterns.proxy.common.Agent;
import com.jdr.designpatterns.proxy.common.Celebrity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 *
 * @author zhoude
 * @date 2019/10/22 9:48
 */
public class DynamicProxyDemo implements InvocationHandler {

    private Object target;

    public DynamicProxyDemo(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        after();
        Object invoke = method.invoke(target, args);
        before();
        return invoke;
    }

    private void after() {
        System.err.println("after");
    }

    private void before() {
        System.err.println("before");
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}

class DynamicProxyClient {

    public static void main(String[] args) {
        DynamicProxyDemo dynamic = new DynamicProxyDemo(new Celebrity());
        Agent agent = (Agent) dynamic.getProxy();
        agent.advertising();
        agent.collectMoney();
        agent.travelArrangements();
    }
}
