package com.lenovo.dpc.threads;

import com.lenovo.dpc.config.DpcConfig;
import com.qkforex.springbootstudy.chapter03.multithread.CustomAsyncScheduler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomeThreadPoolExecutor {
    private volatile static CustomeThreadPoolExecutor instance;
    private static ThreadPoolExecutor chnlBackendQueryPool;
    @Autowired
    private static DpcConfig dpcConfig;

    private CustomeThreadPoolExecutor() {
    }

    @SuppressWarnings({"rawtypes", "static-access", "unchecked"})
    public static CustomeThreadPoolExecutor getInstance() {
        if (instance == null) {
            synchronized (CustomAsyncScheduler.class) {
                if (instance == null) {
                    instance = new CustomeThreadPoolExecutor();
                    BlockingQueue queue = new LinkedBlockingQueue();
                    chnlBackendQueryPool = new ThreadPoolExecutor(dpcConfig.getCorePoolSize(), dpcConfig.getMaxPoolSize(), dpcConfig.getKeepAliveTime(), TimeUnit.SECONDS, queue);
                    chnlBackendQueryPool.allowCoreThreadTimeOut(true);
                    instance.setChnlBackendQueryPool(chnlBackendQueryPool);
                }
            }
        }
        return instance;
    }

    public ThreadPoolExecutor getChnlBackendQueryPool() {
        return chnlBackendQueryPool;
    }

    public static void setChnlBackendQueryPool(ThreadPoolExecutor chnlBackendQueryPool) {
        CustomeThreadPoolExecutor.chnlBackendQueryPool = chnlBackendQueryPool;
    }

    public static void setInstance(CustomeThreadPoolExecutor instance) {
        CustomeThreadPoolExecutor.instance = instance;
    }

}
