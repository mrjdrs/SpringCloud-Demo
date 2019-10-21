package com.jdr.designpatterns.proxy;

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

    private void after() {
        System.err.println("after");
    }

    private void before() {
        System.err.println("before");
    }

    public static void main(String[] args) {
        Agent agent = new ProxyDemo(new Celebrity());
        agent.advertising();

        System.out.println("----------------------------");

        Agent agent2 = new ProxyDemo(new Celebrity2());
        agent2.advertising();
    }
}

interface Agent {
    /**
     * 拍广告
     */
    void advertising();
}

class Celebrity implements Agent {
    @Override
    public void advertising() {
        System.err.println("明星拍广告");
    }
}

class Celebrity2 implements Agent {
    @Override
    public void advertising() {
        System.err.println("明星2拍广告");
    }
}