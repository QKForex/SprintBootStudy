package com.qkforex.springbootstudy;

import com.qkforex.springbootstudy.chapter02.el.ElConfig;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude = {
        MongoAutoConfiguration.class,
        RedisAutoConfiguration.class,
        DataSourceAutoConfiguration.class})
@ComponentScan("com.qkforex.springbootstudy.chapter02.el")
//@ComponentScan("com.lenovo.dpc.config")
public class SpringBootStudyApplication {
    public static void main(String[] args) {
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder();
        springApplicationBuilder.web(WebApplicationType.NONE).sources(SpringBootStudyApplication.class).run(args);;
        ConfigurableApplicationContext context = springApplicationBuilder.context();
        System.out.println(context);
        //System.out.println(context.getEnvironment().getActiveProfiles());
        //ElConfig elConfig = context.getBean(ElConfig.class);
        //elConfig.outResource();

        //new SpringApplicationBuilder().web().sources(SpringBootStudyApplication.class).run(args);

    }
}
