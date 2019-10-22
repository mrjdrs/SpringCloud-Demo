package com.jdr.designpatterns.proxy.common;

/**
 * @author zhoude
 * @date 2019/10/22 9:48
 */
public class Celebrity2 implements Agent {

    @Override
    public void advertising() {
        System.err.println("Celebrity2拍广告");
    }

    @Override
    public void collectMoney() {
        System.err.println("Celebrity2集资");
    }
}
