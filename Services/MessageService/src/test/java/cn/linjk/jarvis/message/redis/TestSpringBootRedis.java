package cn.linjk.jarvis.message.redis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: TestSpringBootRedis
 * @author: linjk
 * @date: 2022/4/9 下午11:09
 * @description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestSpringBootRedis {
    @Autowired private RedisTemplate redisTemplate;

    @Test
    public void testRedisTemplate() {
        String pong = redisTemplate.getConnectionFactory().getConnection().ping();
        Assert.assertEquals("应为PONG", "PONG", pong);
        redisTemplate.opsForValue().set("test:jarvis:name", "jarvis");
        String res = redisTemplate.opsForValue().get("test:jarvis:name").toString();
        Assert.assertEquals("应为jarvis", "jarvis", res);
    }
}
