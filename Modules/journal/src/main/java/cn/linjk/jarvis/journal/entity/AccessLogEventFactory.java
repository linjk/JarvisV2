package cn.linjk.jarvis.journal.entity;

import com.lmax.disruptor.EventFactory;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: AccessLogEventFactory
 * @author: linjk
 * @date: 2021/9/2 上午12:02
 * @description:
 */
public class AccessLogEventFactory implements EventFactory<AccessLogEvent> {
    @Override
    public AccessLogEvent newInstance() {
        return new AccessLogEvent();
    }
}
