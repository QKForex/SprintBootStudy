package com.qkforex.springbootstudy.chapter02.prepost;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.qkforex.springbootstudy.chapter02.prepost")
public class PrePostConfig {
    @Bean(initMethod = "init", destroyMethod = "destory")
    public BeanWayService beanWayService() {
        return new BeanWayService();
    }

    @Bean
    public JSR250WayService jsr250WayService() {
        return new JSR250WayService();
    }
}
