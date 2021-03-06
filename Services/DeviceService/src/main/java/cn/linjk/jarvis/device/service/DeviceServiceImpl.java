package cn.linjk.jarvis.device.service;

import cn.hutool.crypto.digest.DigestUtil;
import cn.linjk.jarvis.apis.IDeviceInfoApi;
import cn.linjk.jarvis.common.tables.DeviceInfo;
import cn.linjk.jarvis.repos.DeviceInfoRepo;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.utils.StringUtils;
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
    @Autowired private DeviceInfoRepo deviceInfoRepo;
    @Resource private SendVerifyCodeService sendVerifyCodeService;

    @Override
    public DeviceInfo getDeviceInfo(Long id) {
        DeviceInfo deviceInfo = deviceInfoRepo.findDeviceByDeviceId(id);
        Assert.notNull(deviceInfo, String.format("设备ID:%d不存在", id));
        return deviceInfoRepo.findDeviceByDeviceId(id);
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

        Assert.isTrue(Objects.isNull(deviceInfoRepo.findDeviceByDeviceNameame(deviceName)), "设备名称已被注册");

        secretKey = DigestUtil.md5Hex(secretKey);
        deviceInfo.setSecretKey(secretKey);
        DeviceInfo device = deviceInfoRepo.save(deviceInfo);
        if (Objects.nonNull(device)) {
            // 自动登录一次
            // TODO: cn.linjk.jarvis.device.rest.SignInRest.signIn(java.lang.String, java.lang.String, java.lang.String)
        }
        return device;
    }
}
