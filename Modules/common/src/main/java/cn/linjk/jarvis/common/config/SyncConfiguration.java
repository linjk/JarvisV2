package cn.linjk.jarvis.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: SyncConfiguration
 * @author: linjk
 * @date: 2022/12/12 下午10:07
 * @description: 开启异步任务注解@Async支持
 */
@EnableAsync
@Configuration
public class SyncConfiguration {
    /**
     * 使用示例：@Async("jarvisPoolTaskExecutor")
     * 这样就为Async指定了运行时的线程池
     */
    @Bean(name = "jarvisPoolTaskExecutor")
    public ThreadPoolTaskExecutor getJarvisPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        // 线程池维护线程池的最大数量，只有在缓冲队列满了之后才会申请超过核心线程池数的线程
        taskExecutor.setMaxPoolSize(100);
        // 缓存队列
        taskExecutor.setQueueCapacity(50);
        // 当超过了核心线程池之外的线程在空闲时间到达之后会被销毁
        taskExecutor.setKeepAliveSeconds(200);
        taskExecutor.setThreadNamePrefix("jarvis-");
        /*
         * 当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize，如果还有任务到来时会采取的拒绝策略：
         * 1. AbortPolicy: 丢弃任务并抛出RejectedExecutionException异常
         * 2. DiscardPolicy: 丢弃任务但不抛出异常
         * 3. DiscardOldestPolicy: 丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
         * 4. CallerRunsPolicy: 重试添加当前的任务，自动重复调用execute()方法，直到成功
         */
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();

        return taskExecutor;
    }
}
