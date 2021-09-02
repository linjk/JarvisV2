package cn.linjk.jarvis.journal.config;

import cn.linjk.jarvis.journal.entity.AccessLogEvent;
import cn.linjk.jarvis.journal.entity.AccessLogEventFactory;
import cn.linjk.jarvis.journal.handler.AccessLogEventHandler;
import cn.linjk.jarvis.journal.handler.AccessLogEventProducer;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
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
        log.info("starting disruptor...");
        AccessLogEventFactory accessLogEventFactory = new AccessLogEventFactory();
        int ringBufferSize = 1024 * 1024;
        // TODO 待优化，使用自定义的线程池，这里使用固定边界大小的
        ExecutorService executorService =  Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Disruptor<AccessLogEvent> disruptor = new Disruptor<>(
                accessLogEventFactory,
                ringBufferSize,
                executorService,
                ProducerType.SINGLE,
                new BlockingWaitStrategy());
        // 添加消费者监听
        disruptor.handleEventsWith(new AccessLogEventHandler());
        disruptor.start();
        // test
        RingBuffer<AccessLogEvent> accessLogEventRingBuffer = disruptor.getRingBuffer();
        AccessLogEventProducer activeLogEventProducer = new AccessLogEventProducer(accessLogEventRingBuffer);

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (long i = 0; i < 100; i++) {
            byteBuffer.putLong(0, i);
            activeLogEventProducer.sendData(byteBuffer);
        }
//        disruptor.shutdown();
//        executorService.shutdown();
    }
}
