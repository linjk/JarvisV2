package cn.linjk.jarvis.message;

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
@EnableDiscoveryClient
@SpringBootApplication
public class MessageServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageServiceApplication.class, args);
    }
}
