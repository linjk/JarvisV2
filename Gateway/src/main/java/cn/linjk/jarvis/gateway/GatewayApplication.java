package cn.linjk.jarvis.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: GatewayApplication
 * @author: linjk
 * @date: 2022/4/16 下午7:25
 * @description:
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages={
        "cn.linjk.jarvis.gateway",
        "cn.linjk.jarvis.common"
})
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
