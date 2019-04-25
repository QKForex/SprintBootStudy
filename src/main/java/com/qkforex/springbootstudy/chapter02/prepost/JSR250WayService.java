package com.qkforex.springbootstudy.chapter02.prepost;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class JSR250WayService {
    @PostConstruct
    public void init() {
        System.out.println("Jsr250-Init-Method");
    }

    public JSR250WayService() {
        super();
        System.out.println("初始化构造函数-Jsr250WayService");
    }

    @PreDestroy
    public void destory() {
        System.out.println("Jsr250-Destroy-Method");
    }
}
