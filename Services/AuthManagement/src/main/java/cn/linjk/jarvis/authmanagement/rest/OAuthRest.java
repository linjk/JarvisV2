package cn.linjk.jarvis.authmanagement.rest;

import cn.linjk.jarvis.common.bean.ResultInfo;
import cn.linjk.jarvis.common.util.ResultInfoUtil;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: OAuthRest
 * @author: linjk
 * @date: 2022/4/11 下午11:41
 * @description:
 */
@RestController
@RequestMapping("oauth")
public class OAuthRest {
    @Resource private TokenEndpoint tokenEndpoint;
    @Resource private HttpServletRequest request;

    /**
     * 重写原有的接口【/oauth/token】，自定义返回数据
     */
    @PostMapping("token")
    public ResultInfo<Map<String, Object>> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters)
            throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.postAccessToken(principal, parameters).getBody());
    }

    /**
     * 自定义 Token 返回对象
     */
    private ResultInfo<Map<String, Object>> custom(OAuth2AccessToken accessToken) {
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        Map<String, Object> data = new LinkedHashMap<>(token.getAdditionalInformation());
        data.put("accessToken", token.getValue());
        data.put("expireIn", token.getExpiresIn());
        data.put("scopes", token.getScope());
        if (token.getRefreshToken() != null) {
            data.put("refreshToken", token.getRefreshToken().getValue());
        }
        return ResultInfoUtil.buildSuccess(request.getServletPath(), data);
    }
}
