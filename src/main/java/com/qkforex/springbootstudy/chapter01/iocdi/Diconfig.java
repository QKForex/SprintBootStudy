package com.qkforex.springbootstudy.chapter01.iocdi;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//注册当前类为一个配置类
@Configuration
// 自动扫描包下所有使用@Service @Component @Repository @Control 的类，并注册为Bean
@ComponentScan("com.qkforex.springbootstudy.chapter01.iocdi")
public class Diconfig {
}
