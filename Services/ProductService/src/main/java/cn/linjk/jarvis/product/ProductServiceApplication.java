package cn.linjk.jarvis.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: ProductServiceApplication
 * @author: linjk
 * @date: 2021/5/29 下午9:32
 * @description:
 */
@EnableScheduling
@SpringBootApplication
@EntityScan(basePackages = {
        "cn.linjk.jarvis.common"
})
public class ProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}
