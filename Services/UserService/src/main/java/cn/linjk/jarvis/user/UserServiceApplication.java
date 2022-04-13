package cn.linjk.jarvis.user;

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
//@ComponentScan(basePackages = {
//        "cn.linjk.jarvis.user"
//        //"cn.linjk.jarvis.journal"
//})
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
