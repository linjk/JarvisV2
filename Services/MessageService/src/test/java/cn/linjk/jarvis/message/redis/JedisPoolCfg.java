package cn.linjk.jarvis.message.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: JedisPool
 * @author: linjk
 * @date: 2022/4/9 下午10:14
 * @description:
 */
public class JedisPoolCfg {
    private static JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(5);
        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMinIdle(0);
        // 等待时间
        jedisPoolConfig.setMaxWaitMillis(100);
        jedisPool = new JedisPool(jedisPoolConfig,
                "127.0.0.1", 6379, 100, "xxx");
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
