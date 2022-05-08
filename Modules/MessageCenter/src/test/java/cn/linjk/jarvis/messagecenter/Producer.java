package cn.linjk.jarvis.messagecenter;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: Producer
 * @author: linjk
 * @date: 2022/5/9 上午12:17
 * @description:
 */
public class Producer {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("test_jarvis_producer");
        producer.setNamesrvAddr("10.211.55.10:9876");
        try {
            producer.start();
            Message message = new Message("test-jarvis", "tag-1", "001", "Hello".getBytes());
            SendResult send = producer.send(message);
            System.out.println("消息发送结果: " + send);
            producer.shutdown();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
