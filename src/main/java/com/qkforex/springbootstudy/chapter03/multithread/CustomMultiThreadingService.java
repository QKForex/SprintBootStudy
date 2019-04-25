package com.qkforex.springbootstudy.chapter03.multithread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Description: 创建线程任务服务
 * @ClassName: CustomMultiThreadingService
 * @Author: OnlyMate
 * @Date: 2018年9月21日 下午3:17:57
 */
@Service
@Slf4j
public class CustomMultiThreadingService {
    //private log log = logFactory.getlog(CustomMultiThreadingService.class);

    /**
     * @param i
     * @Description:通过@Async注解表明该方法是一个异步方法， 如果注解在类级别上，则表明该类所有的方法都是异步方法，而这里的方法自动被注入使用ThreadPoolTaskExecutor作为TaskExecutor
     * @Title: executeAysncTask1
     * @Date: 2018年9月21日 下午2:54:32
     * @Author: OnlyMate
     * @Throws
     */
    @Async
    public void executeAysncTask1(Integer i) {
        log.info("CustomMultiThreadingService ==> executeAysncTask1 method: 执行异步任务{} ", i);
    }

    /**
     * @param i
     * @Description:通过@Async注解表明该方法是一个异步方法， 如果注解在类级别上，则表明该类所有的方法都是异步方法，而这里的方法自动被注入使用ThreadPoolTaskExecutor作为TaskExecutor
     * @Title: executeAsyncTask2
     * @Date: 2018年9月21日 下午2:55:04
     * @Author: OnlyMate
     * @Throws
     */
    @Async
    public void executeAsyncTask2(Integer i) {
        log.info("CustomMultiThreadingService ==> executeAsyncTask2 method: 执行异步任务{} ", i);
    }

    /**
     * @param i
     * @Description: 异步线程调度管理器创建线程任务
     * @Title: executeAsyncTask3
     * @Date: 2018年9月21日 下午3:32:28
     * @Author: OnlyMate
     * @Throws
     */
    public void executeAsyncTask3(Integer i) {
        CustomAsyncScheduler.getInstance().getChnlBackendQueryPool().execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("executeAsyncTask3 method: 执行异步任务{} " + i);
            }
        });
    }
}