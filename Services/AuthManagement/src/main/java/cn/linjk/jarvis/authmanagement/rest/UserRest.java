package cn.linjk.jarvis.authmanagement.rest;

import cn.linjk.jarvis.common.bean.ResultInfo;
import cn.linjk.jarvis.common.bean.SignInIdentity;
import cn.linjk.jarvis.common.tables.DeviceInfo;
import cn.linjk.jarvis.common.util.ResultInfoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: UserRest
 * @author: linjk
 * @date: 2022/4/12 下午11:47
 * @description:
 */
@Api("用户信息")
@Slf4j
@RestController
public class UserRest {
    @Resource private HttpServletRequest request;
    @Resource private RedisTokenStore redisTokenStore;

    @ApiOperation("获取登录用户信息")
    @GetMapping("user/me")
    public ResultInfo getCurrentUser(Authentication authentication) {
        // 获取登录用户的信息，然后设置
        SignInIdentity signInIdentity = (SignInIdentity) authentication.getPrincipal();
        // 转为前端可用的视图对象
        DeviceInfo dinerInfo = new DeviceInfo();
        BeanUtils.copyProperties(signInIdentity, dinerInfo);
        return ResultInfoUtil.buildSuccess(request.getServletPath(), dinerInfo);
    }

    /**
     * 安全退出
     *
     * @param access_token
     * @param authorization
     * @return
     */
    @GetMapping("user/logout")
    public ResultInfo logout(String access_token, String authorization) {
        if (StringUtils.isBlank(access_token)) {
            access_token = authorization;
        }
        // 判断 authorization 是否为空
        if (StringUtils.isBlank(access_token)) {
            return ResultInfoUtil.buildSuccess(request.getServletPath(), "退出成功");
        }
        // 判断 bearer token 是否为空
        if (access_token.toLowerCase().contains("bearer ".toLowerCase())) {
            access_token = access_token.toLowerCase().replace("bearer ", "");
        }
        // 清除 redis token 信息
        OAuth2AccessToken oAuth2AccessToken = redisTokenStore.readAccessToken(access_token);
        if (oAuth2AccessToken != null) {
            redisTokenStore.removeAccessToken(oAuth2AccessToken);
            OAuth2RefreshToken refreshToken = oAuth2AccessToken.getRefreshToken();
            redisTokenStore.removeRefreshToken(refreshToken);
        }
        return ResultInfoUtil.buildSuccess(request.getServletPath(), "退出成功");
    }
}
