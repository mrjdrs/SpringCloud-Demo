package com.jdr.designpatterns.proxy;

import com.jdr.designpatterns.proxy.common.Agent;
import com.jdr.designpatterns.proxy.common.Celebrity;
import com.jdr.designpatterns.proxy.common.Celebrity2;

/**
 * 代理模式
 * 经纪人代理明星接广告：对于明星来说他的主要任务只是拍广告，而接广告这类事情他不关心
 *
 * @author zhoude
 * @date 2019/10/21 17:17
 */
public class ProxyDemo implements Agent {

    private Agent agent;

    public ProxyDemo(Agent agent) {
        this.agent = agent;
    }

    @Override
    public void advertising() {
        after();
        agent.advertising();
        before();
    }

    @Override
    public void collectMoney() {
        after();
        agent.collectMoney();
        before();
    }

    @Override
    public void travelArrangements() {
        after();
        agent.collectMoney();
        before();
    }

    private void after() {
        System.err.println("after");
    }

    private void before() {
        System.err.println("before");
    }
}

class ProxyClient {

    public static void main(String[] args) {
        Agent agent = new ProxyDemo(new Celebrity());
        agent.advertising();
        agent.collectMoney();

        System.out.println("----------------------------");

        Agent agent2 = new ProxyDemo(new Celebrity2());
        agent2.advertising();
        agent2.collectMoney();
    }
}