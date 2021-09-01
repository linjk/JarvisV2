package cn.linjk.jarvis.common.test;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.junit.Test;

import java.util.concurrent.Executors;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: TestDisruptor
 * @author: linjk
 * @date: 2021/9/1 下午11:10
 * @description:
 */
public class TestDisruptor {
    @Test
    public void test() throws Exception {
        int ringBufferSize = 65536;
        final Disruptor<Data> disruptor = new Disruptor<Data>(
                new EventFactory<Data>() {
                    @Override
                    public Data newInstance() {
                        return new Data();
                    }
                },
                ringBufferSize,
                Executors.newSingleThreadExecutor(),
                ProducerType.SINGLE,
                new YieldingWaitStrategy()
        );
        DataConsumer consumer = new DataConsumer();
        disruptor.handleEventsWith(consumer);
        disruptor.start();

        new Thread(() -> {
            RingBuffer<Data> ringBuffer = disruptor.getRingBuffer();
            for (long i = 0; i < Constants.EVENT_NUM_OM; i++) {
                long seq = ringBuffer.next();
                Data data = ringBuffer.get(seq);
                data.setId(i);
                data.setName("c" + i);
                ringBuffer.publish(seq);
            }
        }).start();

        Thread.sleep(10000);
    }
}
