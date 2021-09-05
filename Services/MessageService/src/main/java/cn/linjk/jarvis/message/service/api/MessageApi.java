package cn.linjk.jarvis.message.service.api;

import cn.linjk.jarvis.api.message.IMessageApi;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: MessageApi
 * @author: linjk
 * @date: 2021/7/20 下午10:15
 * @description:
 */
@DubboService(version = "1.0.0", group = "message")
public class MessageApi implements IMessageApi {
    @Override
    public boolean sendMessage(Integer messageType, String messageContent) {
        System.out.println("即将发送消息......");
        return false;
    }
}
