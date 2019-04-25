package com.qkforex.springbootstudy.chapter03.multithread;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExecuterConfig.class);
        AsyncTaskService asyncTaskService = context.getBean(AsyncTaskService.class);
        CustomMultiThreadingService customMultiThreadingService = context.getBean(CustomMultiThreadingService.class);
        for (int i = 0; i < 3; i++) {
            asyncTaskService.executeTask(i);
            //asyncTaskService.executeTaskPlus(i);
            //customMultiThreadingService.executeAsyncTask3(i);
        }
        context.close();
    }
}
