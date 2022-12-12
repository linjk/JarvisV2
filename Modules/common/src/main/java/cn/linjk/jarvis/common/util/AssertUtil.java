package cn.linjk.jarvis.common.util;

import cn.linjk.jarvis.common.bean.Constant;
import cn.linjk.jarvis.common.exception.JarvisException;
import org.apache.commons.lang3.StringUtils;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: AssertUtil
 * @author: linjk
 * @date: 2022/4/10 下午6:31
 * @description:
 */
public class AssertUtil {
    /**
     * 判断字符串非空
     *
     * @param str
     * @param message
     */
    public static void isNotEmpty(String str, String... message) {
        if (StringUtils.isBlank(str)) {
            execute(message);
        }
    }

    /**
     * 判断对象非空
     *
     * @param obj
     * @param message
     */
    public static void isNotNull(Object obj, String... message) {
        if (obj == null) {
            execute(message);
        }
    }

    /**
     * 判断结果是否为真
     *
     * @param isTrue
     * @param message
     */
    public static void isTrue(boolean isTrue, String... message) {
        if (isTrue) {
            execute(message);
        }
    }

    /**
     * 最终执行方法
     *
     * @param message
     */
    private static void execute(String... message) {
        String msg = Constant.ERROR_MESSAGE;
        if (message != null && message.length > 0) {
            msg = message[0];
        }
        throw new JarvisException(msg);
    }
}
