package com.qkforex.springbootstudy.chapter02.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig.class);
        DemoSingletomService s1 = context.getBean(DemoSingletomService.class);
        DemoSingletomService s2 = context.getBean(DemoSingletomService.class);
        System.out.println(s1 == s2);
        DemoPrototypeService p1 = context.getBean(DemoPrototypeService.class);
        DemoPrototypeService p2 = context.getBean(DemoPrototypeService.class);
        System.out.println(p1 == p2);
    }
}
