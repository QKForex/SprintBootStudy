package com.qkforex.springbootstudy.chapter03.multithread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskService {

    @Async
    public void executeTask(Integer i) {
        System.out.println("开始执行异步任务 " + i);
        System.out.println("执行异步任务 " + i);
        System.out.println("执行异步任务结束 " + i);

    }

    @Async
    public void executeTaskPlus(Integer i) {
        System.out.println("执行异步任务Plus " + i);
    }
}
