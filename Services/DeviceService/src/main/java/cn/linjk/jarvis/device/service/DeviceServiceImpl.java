package cn.linjk.jarvis.device.service;

import cn.hutool.crypto.digest.DigestUtil;
import cn.linjk.jarvis.apis.IDeviceInfoApi;
import cn.linjk.jarvis.common.mybatis.entity.DeviceInfo;
import cn.linjk.jarvis.common.mybatis.mapper.DeviceInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: DeviceServiceImpl
 * @author: linjk
 * @date: 2022/4/14 上午12:28
 * @description:
 */
@Slf4j
@DubboService(version = "1.0.0", group = "user-service")
public class DeviceServiceImpl implements IDeviceInfoApi {
    @Autowired private DeviceInfoMapper deviceInfoMapper;
    @Resource private SendVerifyCodeService sendVerifyCodeService;

    @Override
    public DeviceInfo getDeviceInfo(Long id) {
        DeviceInfo tmp = new DeviceInfo();
        tmp.setId(id);
        DeviceInfo deviceInfo = deviceInfoMapper.selectByPrimaryKey(tmp);
        Assert.notNull(deviceInfo, String.format("设备ID:%d不存在", id));
        return deviceInfo;
    }

    @Override
    public DeviceInfo registerDevice(DeviceInfo deviceInfo) {
        String deviceName = deviceInfo.getDeviceName().trim();
        String secretKey = deviceInfo.getSecretKey().trim();
        String phone = deviceInfo.getPhone();
        String verifyCode = deviceInfo.getVerifyCode();
        Assert.hasText(deviceName, "设备名称不能为空");
        Assert.hasText(secretKey, "设备登录密码不能为空");
        Assert.hasText(phone, "设备登录密码不能为空");
        Assert.hasText(verifyCode, "验证码不能为空");
        String redisVerifyCode = sendVerifyCodeService.getVerifyCodeByPhone(phone);
        Assert.hasText(redisVerifyCode, "验证码已过期");
        Assert.isTrue(verifyCode.equals(redisVerifyCode), "验证码不匹配");

        DeviceInfo tmp = new DeviceInfo();
        tmp.setDeviceName(deviceName);
        Assert.isTrue(Objects.isNull(deviceInfoMapper.selectOne(tmp)), "设备名称已被注册");

        secretKey = DigestUtil.md5Hex(secretKey);
        deviceInfo.setSecretKey(secretKey);
        int cnt = deviceInfoMapper.insert(deviceInfo);
        if (cnt > 0) {
            // 自动登录一次
            // TODO: cn.linjk.jarvis.device.rest.SignInRest.signIn(java.lang.String, java.lang.String, java.lang.String)
        }
        return deviceInfo;
    }
}
