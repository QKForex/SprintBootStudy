package com.qkforex.springbootstudy.chapter02.prepost;

public class BeanWayService {
    public void init() {
        System.out.println("@Bean-Init-Method");
    }

    public BeanWayService() {
        super();
        System.out.println("初始化构造函数-BeanWayService");
    }

    public void destory() {
        System.out.println("@Bean-Destroy-Method");
    }
}
