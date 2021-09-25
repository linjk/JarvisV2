package cn.linjk.jarvis.common.test.common;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.junit.Test;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: TestUuid
 * @author: linjk
 * @date: 2021/9/26 上午12:38
 * @description:
 */
public class TestUuid {
    /**
     * 带时间顺序的UUID生成测试
     */
    @Test
    public void testGetUuid() {
        TimeBasedGenerator timeGenerator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
        System.out.println(timeGenerator.generate().toString());
    }
}
