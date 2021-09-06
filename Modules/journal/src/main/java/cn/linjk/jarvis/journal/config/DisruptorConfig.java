package cn.linjk.jarvis.journal.config;

import cn.linjk.jarvis.journal.entity.AccessLogEvent;
import cn.linjk.jarvis.journal.handler.AccessLogConsumer;
import cn.linjk.jarvis.journal.handler.AccessLogProducer;
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: DisruptorConfig
 * @author: linjk
 * @date: 2021/9/2 下午9:41
 * @description:
 */
@Slf4j
@Component
public class DisruptorConfig implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        RingBuffer<AccessLogEvent> ringBuffer = RingBuffer.create(
                ProducerType.MULTI,
                AccessLogEvent::new,
                1024 * 1024,
                new YieldingWaitStrategy()
        );
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
        AccessLogConsumer[] consumers = new AccessLogConsumer[10];
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new AccessLogConsumer("C" + i);
        }
        WorkerPool<AccessLogEvent> accessLogEventWorkerPool = new WorkerPool<>(
                ringBuffer,
                sequenceBarrier,
                new ExceptionHandler<AccessLogEvent>() {
                    @Override
                    public void handleEventException(Throwable ex, long sequence, AccessLogEvent event) {
                        System.out.println("消费消息时出现异常");
                    }

                    @Override
                    public void handleOnStartException(Throwable ex) {

                    }

                    @Override
                    public void handleOnShutdownException(Throwable ex) {

                    }
                },
                consumers
        );
        // 设置多个消费者的sequence用于每个消费者单独统计消费消息的进度
        ringBuffer.addGatingSequences(accessLogEventWorkerPool.getWorkerSequences());
        accessLogEventWorkerPool.start(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

        // 生产数据测试
        CountDownLatch latch = new CountDownLatch(1);

        for (int i = 0; i < 100; i++) {
            AccessLogProducer producer = new AccessLogProducer(ringBuffer);
            new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        latch.await();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }

                    for (int j = 0; j < 100; j++) {
                        producer.onData(UUID.randomUUID().toString());
                    }
                }
            }).start();
        }
        Thread.sleep(2000);
        System.out.println("开始生产发布数据...");
        latch.countDown();
        Thread.sleep(10000);
        System.out.println("任务总数: " + consumers[0].getCount());
    }
}
