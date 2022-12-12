package cn.linjk.jarvis.common.config;

import cn.linjk.jarvis.common.bean.RedisLock;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

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
    @Resource private RedisTemplate redisTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RedisLock redisLock() {
        return new RedisLock(redisTemplate);
    }
}
