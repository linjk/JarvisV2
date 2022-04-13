package cn.linjk.jarvis.message;

import cn.linjk.jarvis.api.message.IDeviceInfoApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: MessageServiceApplication
 * @author: linjk
 * @date: 2021/7/20 上午12:25
 * @description:
 */
@EnableDubbo
@EnableDiscoveryClient
@SpringBootApplication
public class MessageServiceApplication {
    @DubboReference(version = "1.0.0", group = "user-service")
    public IDeviceInfoApi deviceInfoApi;

    public static void main(String[] args) {
        SpringApplication.run(MessageServiceApplication.class, args);
    }
}
