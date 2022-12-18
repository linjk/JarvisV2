package cn.linjk.jarvis.common.bean;

import cn.linjk.jarvis.common.util.ResultInfoUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: ResponseHandler
 * @author: linjk
 * @date: 2022/12/13 上午12:04
 * @description: 全局返回处理器，这里暂时不启用
 */
@ControllerAdvice(basePackages = "cn.linjk.jarvis")
public class ResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof ResultInfo) {
            ResultInfo resultInfo = (ResultInfo) o;
            return ResultInfoUtil.buildSuccess("");
        }
        return ResultInfoUtil.buildSuccess("");
    }
}
