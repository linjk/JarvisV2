package cn.linjk.jarvis.common.util;

import cn.linjk.jarvis.common.threadpool.CpuIntenseTargetThreadPoolLazyHolder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: ThreadUtil
 * @author: linjk
 * @date: 2022/5/12 上午1:39
 * @description:
 */
public class ThreadUtil {
    /**
     * CPU核数
     **/
    public static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    /**
     * 核心线程数
     */
    public static final int CORE_POOL_SIZE = 0;
    public static final int MAXIMUM_POOL_SIZE = CPU_COUNT;

    /**
     * 空闲保活时限，单位秒
     */
    public static final int KEEP_ALIVE_SECONDS = 30;

    /**
     * 有界队列size
     */
    public static final int QUEUE_SIZE = 10000;

    /**
     * 获取执行CPU密集型任务的线程池
     *
     * @return
     */
    public static ThreadPoolExecutor getCpuIntenseTargetThreadPool() {
        return CpuIntenseTargetThreadPoolLazyHolder.getInnerExecutor();
    }

    public static void shutdownThreadPoolGracefully(ExecutorService threadPool) {
        if (!(threadPool instanceof ExecutorService) || threadPool.isTerminated()) {
            return;
        }
        try {
            threadPool.shutdown();   //拒绝接受新任务
        }
        catch (SecurityException e) {
            return;
        }
        catch (NullPointerException e) {
            return;
        }
        try {
            // 等待 60 s，等待线程池中的任务完成执行
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                // 调用 shutdownNow 取消正在执行的任务
                threadPool.shutdownNow();
                // 再次等待 60 s，如果还未结束，可以再次尝试，或则直接放弃
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("线程池任务未正常执行结束");
                }
            }
        }
        catch (InterruptedException ie) {
            // 捕获异常，重新调用 shutdownNow
            threadPool.shutdownNow();
        }
        //任然没有关闭，循环关闭1000次，每次等待10毫秒
        if (!threadPool.isTerminated()) {
            try {
                for (int i = 0; i < 1000; i++) {
                    if (threadPool.awaitTermination(10, TimeUnit.MILLISECONDS)) {
                        break;
                    }
                    threadPool.shutdownNow();
                }
            }
            catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            catch (Throwable e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static class CustomThreadFactory implements ThreadFactory {
        //线程池数量
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;

        //线程数量
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String threadTag;

        public CustomThreadFactory(String threadTag) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.threadTag = "apppool-" + poolNumber.getAndIncrement() + "-" + threadTag + "-";
        }

        @Override
        public Thread newThread(Runnable target) {
            Thread t = new Thread(group, target, threadTag + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}
