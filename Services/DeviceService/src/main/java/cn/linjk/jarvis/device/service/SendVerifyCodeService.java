package cn.linjk.jarvis.device.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.linjk.jarvis.common.bean.RedisKeyConstant;
import cn.linjk.jarvis.common.util.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: SendVerifyCodeService
 * @author: linjk
 * @date: 2022/5/2 下午1:33
 * @description:
 */
@Service
public class SendVerifyCodeService {
    @Resource private RedisTemplate<String, String> redisTemplate;

    public void send(String phone) {
        AssertUtil.isNotEmpty(phone, "手机号不能为空");
        if(!checkCodeIsExpired(phone)) {
            return;
        }
        String code = RandomUtil.randomNumbers(6);
        String key = RedisKeyConstant.VERIFY_CODE.getKey() + phone;
        redisTemplate.opsForValue().set(key, code, 60, TimeUnit.SECONDS);
    }

    public String getVerifyCodeByPhone(String phone) {
        String key = RedisKeyConstant.VERIFY_CODE.getKey() + phone;
        return redisTemplate.opsForValue().get(key);
    }

    private boolean checkCodeIsExpired(String phone) {
        String key = RedisKeyConstant.VERIFY_CODE.getKey() + phone;
        String code = redisTemplate.opsForValue().get(key);
        if(StrUtil.isBlank(code)) {
            return true;
        }
        return false;
    }
}
