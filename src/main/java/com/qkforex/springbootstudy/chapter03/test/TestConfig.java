package com.qkforex.springbootstudy.chapter03.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan("com.qkforex.springbootstudy.chapter03.test")
public class TestConfig {
    @Bean
    @Profile("dev")
    public TestBean devTestBeabn() {
        return new TestBean("from development profile");
    }

    @Bean
    @Profile("prod")
    public TestBean prodTestBeabn() {
        return new TestBean("from production profile");
    }
}
