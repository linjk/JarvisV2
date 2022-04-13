package cn.linjk.jarvis.authmanagement.config;

import cn.linjk.jarvis.common.bean.Constant;
import cn.linjk.jarvis.common.bean.ResultInfo;
import cn.linjk.jarvis.common.util.ResultInfoUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: MyAuthenticationEntryPoint
 * @author: linjk
 * @date: 2022/4/12 下午11:55
 * @description: 认证失败处理
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter out = response.getWriter();
        String errorMessage = authException.getMessage();
        if (StringUtils.isBlank(errorMessage)) {
            errorMessage = "登录失效!";
        }
        ResultInfo result = ResultInfoUtil.buildError(Constant.ERROR_CODE, errorMessage, request.getRequestURI());
        out.write(objectMapper.writeValueAsString(result));
        out.flush();
        out.close();
    }
}
