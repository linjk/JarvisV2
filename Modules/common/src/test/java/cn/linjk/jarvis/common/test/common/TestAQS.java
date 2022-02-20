package cn.linjk.jarvis.common.test.common;

import org.junit.Test;

import java.util.concurrent.locks.Lock;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: TestAQS
 * @author: linjk
 * @date: 2021/9/10 上午12:22
 * @description:
 */
public class TestAQS {
    @Test
    public void testLock() throws Exception {
        Lock lock = null;
        // 获取锁，成功继续执行，失败一直阻塞
        lock.lock();
        try {
            // your code
        }
        finally {
            lock.unlock();
        }
    }
}
