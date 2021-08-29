package cn.linjk.jarvis.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: BeanConfig
 * @author: linjk
 * @date: 2021/8/29 下午11:18
 * @description:
 */
@Configuration
public class BeanConfig {
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(
        RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        //采用普通的key 为 字符串
        template.setKeySerializer(new StringRedisSerializer());
        return template;
    }
}
