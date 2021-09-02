package cn.linjk.jarvis.journal.handler;

import cn.linjk.jarvis.journal.entity.AccessLogEvent;
import com.lmax.disruptor.EventHandler;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: AccessLogEventHandler
 * @author: linjk
 * @date: 2021/9/2 上午12:06
 * @description:
 */
public class AccessLogEventHandler implements EventHandler<AccessLogEvent> {
    @Override
    public void onEvent(AccessLogEvent accessLogEvent, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费: " + sequence);
    }
}
