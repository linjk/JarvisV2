package cn.linjk.jarvis.journal.handler;

import cn.linjk.jarvis.journal.entity.AccessLogEvent;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: AccessLogEventProducer
 * @author: linjk
 * @date: 2021/9/2 下午10:07
 * @description:
 */
public class AccessLogEventProducer {
    private RingBuffer<AccessLogEvent> ringBuffer;

    public AccessLogEventProducer(RingBuffer<AccessLogEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void sendData(ByteBuffer byteBuffer) {
        // 在生产者发送消息的时候，需要先从ringBuffer取一个可用的序号
        long seq = ringBuffer.next();
        try {
            // 根据这个序号，找到具体的event元素，此时元素元素未初始化，需要进行值设定
            AccessLogEvent accessLogEvent = ringBuffer.get(seq);
            //accessLogEvent.set(byteBuffer.getLong(0));
        }
        finally {
            // 提交
            ringBuffer.publish(seq);
        }
    }
}
