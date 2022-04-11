package cn.linjk.jarvis.authmanagement.common;

import cn.hutool.crypto.digest.DigestUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: CommonTest
 * @author: linjk
 * @date: 2022/4/11 下午10:55
 * @description:
 */
public class CommonTest {
    @Test
    public void testHutoolMd5() {
        String md5String = DigestUtil.md5Hex("light123456");
        Assert.assertNotNull(md5String);
        System.out.println(md5String);
    }
}
