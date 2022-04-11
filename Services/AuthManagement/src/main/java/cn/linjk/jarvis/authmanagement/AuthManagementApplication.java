package cn.linjk.jarvis.authmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: AuthManagementApplication
 * @author: linjk
 * @date: 2022/4/11 下午8:50
 * @description:
 */
@EnableDiscoveryClient
@SpringBootApplication
@EntityScan(basePackages = {
        "cn.linjk.jarvis.common.tables"
})
public class AuthManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthManagementApplication.class, args);
    }
}
