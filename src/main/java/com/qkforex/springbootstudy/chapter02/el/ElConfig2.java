package com.qkforex.springbootstudy.chapter02.el;

import lombok.Getter;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan("com.qkforex.springbootstudy.chapter02.el")
@PropertySource("classpath:application.properties")
public class ElConfig2 {

    @Value("${logging.file}")
    @Getter
    private String logfile;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfiguration() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
