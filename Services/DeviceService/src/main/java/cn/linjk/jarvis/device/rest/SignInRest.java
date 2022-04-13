package cn.linjk.jarvis.device.rest;

import cn.hutool.core.bean.BeanUtil;
import cn.linjk.jarvis.common.bean.Constant;
import cn.linjk.jarvis.common.bean.ResultInfo;
import cn.linjk.jarvis.common.util.AssertUtil;
import cn.linjk.jarvis.common.util.ResultInfoUtil;
import cn.linjk.jarvis.device.bean.OAuthUserInfo;
import cn.linjk.jarvis.device.config.OAuth2ClientConfiguration;
import cn.linjk.jarvis.device.dto.LoginUserInfo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: SignInRest
 * @author: linjk
 * @date: 2022/4/12 下午9:58
 * @description:
 */
@Slf4j
@RestController
@Api(tags = "登录接口")
public class SignInRest {
    @Resource private RestTemplate restTemplate;
    @Value("${service.name.jarvis-oauth-server}")
    private String oauthServerName;
    @Resource private OAuth2ClientConfiguration oAuth2ClientConfiguration;
    @Resource private HttpServletRequest request;

    @GetMapping("signin")
    public ResultInfo signIn(String account, String password) {
        return this.signIn(account, password, request.getServletPath());
    }

    /**
     * 登录
     *
     * @param account  帐号：用户名或手机或邮箱
     * @param password 密码
     * @param path     请求路径
     * @return
     */
    private ResultInfo signIn(String account, String password, String path) {
        // 参数校验
        AssertUtil.isNotEmpty(account, "请输入登录帐号");
        AssertUtil.isNotEmpty(password, "请输入登录密码");
        // 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // 构建请求体（请求参数）
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("username", account);
        body.add("password", password);
        body.setAll(BeanUtil.beanToMap(oAuth2ClientConfiguration));
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
        // 设置 Authorization
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(oAuth2ClientConfiguration.getClientId(),
                oAuth2ClientConfiguration.getSecret()));
        // 发送请求
        ResponseEntity<ResultInfo> result = restTemplate.postForEntity(oauthServerName + "oauth/token", entity, ResultInfo.class);
        // 处理返回结果
        AssertUtil.isTrue(result.getStatusCode() != HttpStatus.OK, "登录失败");
        ResultInfo resultInfo = result.getBody();
        if (resultInfo.getCode() != Constant.SUCCESS_CODE) {
            // 登录失败
            resultInfo.setData(resultInfo.getMessage());
            return resultInfo;
        }
        // 这里的 Data 是一个 LinkedHashMap 转成了域对象 OAuthDinerInfo
        OAuthUserInfo userinfo = BeanUtil.fillBeanWithMap((LinkedHashMap) resultInfo.getData(),
                new OAuthUserInfo(), false);
        // 根据业务需求返回视图对象
        LoginUserInfo loginDinerInfo = new LoginUserInfo();
        loginDinerInfo.setToken(userinfo.getAccessToken());
        loginDinerInfo.setAvatarUrl(userinfo.getAvatarUrl());
        loginDinerInfo.setAliasName(userinfo.getAliasName());
        return ResultInfoUtil.buildSuccess(path, loginDinerInfo);
    }
}
