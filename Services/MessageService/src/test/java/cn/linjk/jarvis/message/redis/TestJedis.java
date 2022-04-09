package cn.linjk.jarvis.message.redis;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Objects;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: TestJedis
 * @author: linjk
 * @date: 2022/4/9 下午9:47
 * @description:
 */
public class TestJedis {
    private Jedis jedis = null;

    @Before
    public void testInit() {
        jedis = JedisPoolCfg.getJedis();
        // 检测是否连接redis成功
        String pong = jedis.ping();
        System.out.println("pong = " + pong);
    }

    @Test
    public void testString() {
        String res = jedis.select(1);
        Assert.assertEquals("选择数据库失败", "OK", res);
        res = jedis.set("test:jarvis:name", "jarvis");
        Assert.assertEquals("set失败", "OK", res);
        res = jedis.get("test:jarvis:name");
        Assert.assertEquals("get失败", "jarvis", res);
    }

    @After
    public void testFinish() {
        if (Objects.nonNull(jedis)) {
            jedis.close();
        }
    }
}
