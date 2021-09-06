package cn.linjk.jarvis.journal.handler;

import cn.linjk.jarvis.journal.entity.AccessLogEvent;
import com.lmax.disruptor.WorkHandler;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: AccessLogConsumer
 * @author: linjk
 * @date: 2021/9/6 下午10:43
 * @description:
 */
public class AccessLogConsumer implements WorkHandler<AccessLogEvent> {
    private String consumerId;

    private static AtomicInteger count = new AtomicInteger(0);
    private Random random = new Random();

    public AccessLogConsumer(String consumerId) {
        this.consumerId = consumerId;
    }

    @Override
    public void onEvent(AccessLogEvent event) throws Exception {
        Thread.sleep(random.nextInt(5));
        System.out.println("consumerId: " + consumerId + ": " + event.toString());
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}
