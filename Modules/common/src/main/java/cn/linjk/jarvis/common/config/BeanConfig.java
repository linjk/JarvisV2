package cn.linjk.jarvis.common.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: BeanConfig
 * @author: linjk
 * @date: 2022/4/13 下午10:02
 * @description:
 */
@Configuration
public class BeanConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
