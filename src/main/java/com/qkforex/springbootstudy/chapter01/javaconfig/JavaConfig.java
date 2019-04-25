package com.qkforex.springbootstudy.chapter01.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.qkforex.springbootstudy.chapter01.javaconfig")
public class JavaConfig {
    @Bean
    public FunctionService functionService() {
        return new FunctionService();
    }
}
