package cn.linjk.jarvis.authmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: AuthManagementApplication
 * @author: linjk
 * @date: 2022/4/11 下午8:50
 * @description:
 */
@MapperScan(basePackages = {"cn.linjk.jarvis.common.mybatis.mapper"})
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {
        "cn.linjk.jarvis.authmanagement",
        "cn.linjk.jarvis.common"
})
public class AuthManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthManagementApplication.class, args);
    }
}
