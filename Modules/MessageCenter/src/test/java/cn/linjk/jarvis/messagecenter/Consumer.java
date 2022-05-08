package cn.linjk.jarvis.messagecenter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: Consumer
 * @author: linjk
 * @date: 2022/5/9 上午12:48
 * @description:
 */
public class Consumer {
    public static void main(String[] args) {
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("test_jarvis_comsumer");
        pushConsumer.setNamesrvAddr("10.211.55.10:9876");
        pushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        try {
            pushConsumer.subscribe("test-jarvis", "*");
            pushConsumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    MessageExt message = msgs.get(0);
                    try {
                        System.out.println("收到消息: " + new String(message.getBody(), RemotingHelper.DEFAULT_CHARSET));
                    }
                    catch (Exception e) {
                        int count = message.getReconsumeTimes();
                        System.out.println("消息重试次数: " + count);
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            pushConsumer.start();
            System.out.println("消费者启动完成...");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
