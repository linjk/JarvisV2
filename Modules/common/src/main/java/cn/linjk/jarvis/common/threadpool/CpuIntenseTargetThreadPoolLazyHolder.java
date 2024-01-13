package cn.linjk.jarvis.common.threadpool;

import cn.linjk.jarvis.common.util.ShutdownHookThread;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static cn.linjk.jarvis.common.util.ThreadUtil.*;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: CpuIntenseTargetThreadPoolLazyHolder
 * @author: linjk
 * @date: 2022/5/12 上午1:41
 * @description:
 */
@Slf4j
public class CpuIntenseTargetThreadPoolLazyHolder {
    /**
     * 线程池： 用于CPU密集型任务
     */
    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(
            MAXIMUM_POOL_SIZE,
            MAXIMUM_POOL_SIZE,
            KEEP_ALIVE_SECONDS,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue(QUEUE_SIZE),
            new CustomThreadFactory("cpu"));


    public static ThreadPoolExecutor getInnerExecutor() {
        return EXECUTOR;
    }

    static {
        log.info("#CpuIntenseTargetThreadPoolLazyHolder# 线程池已经初始化");

        EXECUTOR.allowCoreThreadTimeOut(true);
        //JVM关闭时的钩子函数
        Runtime.getRuntime().addShutdownHook(
                new ShutdownHookThread("CPU密集型任务线程池", new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        //优雅关闭线程池
                        shutdownThreadPoolGracefully(EXECUTOR);
                        return null;
                    }
                }));
    }
}
