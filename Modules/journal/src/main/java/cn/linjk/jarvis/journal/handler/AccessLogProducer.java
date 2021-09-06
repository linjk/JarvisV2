package cn.linjk.jarvis.journal.handler;

import cn.linjk.jarvis.journal.entity.AccessLogEvent;
import com.lmax.disruptor.RingBuffer;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: AccessLogProducer
 * @author: linjk
 * @date: 2021/9/6 下午11:46
 * @description:
 */
public class AccessLogProducer {
    private RingBuffer<AccessLogEvent> ringBuffer;

    public AccessLogProducer(RingBuffer<AccessLogEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(String data) {
        long seq = ringBuffer.next();
        try {
            AccessLogEvent accessLogEvent = ringBuffer.get(seq);
            accessLogEvent.setPath(data);
        }
        finally {
            ringBuffer.publish(seq);
        }
    }
}
