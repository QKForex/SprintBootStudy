package com.qkforex.springbootstudy.chapter02.el;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;


public class Main {
    /*  @Autowired
      private static ElConfig elConfig;
  */
   /* public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ElConfig.class);
        ElConfig2 resourceService = context.getBean(ElConfig2.class);
        Environment environment = context.getEnvironment();
        // ((ConfigurableEnvironment) environment).setActiveProfiles("prod");
        // ((ConfigurableEnvironment) environment).getSystemEnvironment();
        System.out.println(environment.getDefaultProfiles().length);
        for (Object str : environment.getDefaultProfiles()) {
            System.out.println(str);
        }
        //elConfig.outResource();
        context.close();
    }*/
}
