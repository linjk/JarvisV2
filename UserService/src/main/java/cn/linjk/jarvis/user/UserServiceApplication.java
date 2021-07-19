package cn.linjk.jarvis.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: UserServiceApplication
 * @author: linjk
 * @date: 2021/5/29 下午9:58
 * @description:
 */
@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
