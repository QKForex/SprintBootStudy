package com.qkforex.springbootstudy.chapter02.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DemoListener implements ApplicationListener<DemoEvent> {
    @Override
    public void onApplicationEvent(DemoEvent event) {
        String msg = event.getMsg();
        System.out.println("我(bean-DemoListener)接收到了(bean-Demopuhlisher)发布的消息" + msg);
    }
}
