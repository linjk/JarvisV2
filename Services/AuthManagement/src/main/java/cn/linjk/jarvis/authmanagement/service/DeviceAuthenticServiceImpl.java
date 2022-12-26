package cn.linjk.jarvis.authmanagement.service;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: UserService
 * @author: linjk
 * @date: 2022/4/11 下午9:31
 * @description: 登录校验
 */

import cn.linjk.jarvis.repos.DeviceInfoRepo;
import cn.linjk.jarvis.common.bean.SignInIdentity;
import cn.linjk.jarvis.common.mybatis.entity.DeviceInfo;
import cn.linjk.jarvis.common.util.AssertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeviceAuthenticServiceImpl implements UserDetailsService {
    @Autowired private DeviceInfoRepo deviceInfoRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AssertUtil.isNotEmpty(username, "设备名称为空");
        DeviceInfo u = new DeviceInfo();
        u.setDeviceName(username);
        DeviceInfo deviceInfo = deviceInfoRepo.findDeviceByDeviceNameame(username);
        if (deviceInfo == null) {
            throw new UsernameNotFoundException("设备名或密码错误，请重新输入");
        }
        SignInIdentity signInIdentity = new SignInIdentity();
        BeanUtils.copyProperties(deviceInfo, signInIdentity);
        return signInIdentity;
    }

}
