package cn.linjk.jarvis.common.test;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

import static cn.linjk.jarvis.common.test.Constants.EVENT_NUM_OHM;
import static cn.linjk.jarvis.common.test.Constants.EVENT_NUM_OM;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: TestArrayBlockingQueue
 * @author: linjk
 * @date: 2021/9/1 下午10:33
 * @description:
 */
public class TestArrayBlockingQueue {
    @Test
    public void test() throws InterruptedException {
        // ArrayBlockingQueue 有界队列
        final ArrayBlockingQueue<Data> queue = new ArrayBlockingQueue<>(EVENT_NUM_OHM);
        final long startTime = System.currentTimeMillis();
        new Thread(() -> {
            long i = 0;
            while (i < EVENT_NUM_OM) {
                Data data = new Data(i, "c" + 1);
                try {
                    queue.put(data);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }).start();

        new Thread(() -> {
            long k = 0;
            while (k < EVENT_NUM_OM) {
                try {
                    queue.take();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                k++;
            }
            long endTime = System.currentTimeMillis();
            System.out.println("ArrayBlockQueue take time: " + (endTime - startTime) + "ms");
        }).start();
        // junit单元测试并不支持多线程,所以要想测试多线程时,在主线程中等待,以确保子线程能够执行完.
        Thread.sleep(10000);
    }
}
