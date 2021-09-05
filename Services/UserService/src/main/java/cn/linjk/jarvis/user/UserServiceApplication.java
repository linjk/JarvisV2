package cn.linjk.jarvis.user;

import cn.linjk.jarvis.api.message.IMessageApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: UserServiceApplication
 * @author: linjk
 * @date: 2021/5/29 下午9:58
 * @description:
 */
@EnableDubbo
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {
        "cn.linjk.jarvis.user",
        "cn.linjk.jarvis.journal"
})
public class UserServiceApplication {
    @DubboReference(version = "1.0.0", group = "message") public IMessageApi messageApi;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
