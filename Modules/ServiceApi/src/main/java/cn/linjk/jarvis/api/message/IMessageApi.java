package cn.linjk.jarvis.api.message;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: IMessageApi
 * @author: linjk
 * @date: 2021/7/20 下午10:11
 * @description:
 */
public interface IMessageApi {
    boolean sendMessage(Integer messageType, String messageContent);
}
