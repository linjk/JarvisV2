package cn.linjk.jarvis.common.test.common;

import cn.linjk.jarvis.common.util.ThreadUtil;
import org.junit.Test;

import java.util.concurrent.ExecutorService;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: TestCAS
 * @author: linjk
 * @date: 2022/5/12 上午1:32
 * @description:
 */
public class TestCAS {
    final int TURNS = 100000000;
    @Test
    public void testAtomicLong() {
        final int TASK_AMOUNT = 10;
        ExecutorService pool = ThreadUtil.getCpuIntenseTargetThreadPool();
    }
}
