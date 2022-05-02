package cn.linjk.jarvis.common.bean;

import lombok.Getter;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: RedisKeyConstant
 * @author: linjk
 * @date: 2022/5/2 下午1:29
 * @description:
 */
@Getter
public enum RedisKeyConstant {
    VERIFY_CODE("verify_code:", "验证码");

    private String key;
    private String desc;

    RedisKeyConstant(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }
}
