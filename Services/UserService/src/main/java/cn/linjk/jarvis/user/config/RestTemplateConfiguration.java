package cn.linjk.jarvis.user.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: RestTemplateConfiguration
 * @author: linjk
 * @date: 2022/4/12 下午9:53
 * @description:
 */
@Configuration
public class RestTemplateConfiguration {
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
