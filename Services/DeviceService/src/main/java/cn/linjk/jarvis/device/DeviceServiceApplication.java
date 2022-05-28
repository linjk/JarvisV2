package cn.linjk.jarvis.device;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
        "cn.linjk.jarvis.device",
        "cn.linjk.jarvis.common"
})
@EntityScan(basePackages = {
        "cn.linjk.jarvis.common.tables"
})
@EnableJpaRepositories(basePackages={
        "cn.linjk.jarvis.repos"
})
public class DeviceServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeviceServiceApplication.class, args);
    }
}
