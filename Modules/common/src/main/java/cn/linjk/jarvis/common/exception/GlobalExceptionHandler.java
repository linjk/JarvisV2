package cn.linjk.jarvis.common.exception;

import cn.linjk.jarvis.common.bean.Constant;
import cn.linjk.jarvis.common.bean.ResultInfo;
import cn.linjk.jarvis.common.util.ResultInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: GlobalExceptionHandler
 * @author: linjk
 * @date: 2022/5/29 上午12:29
 * @description:
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ParameterException.class)
    public ResultInfo<Map<String, String>> handlerParameterException(ParameterException ex) {
        ResultInfo<Map<String, String>> resultInfo = ResultInfoUtil.buildError(ex.getErrorCode(), ex.getMessage(), "");
        return resultInfo;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResultInfo<Map<String, String>> handlerException(Exception ex) {
        log.info("未知异常：{}", ex);
        ResultInfo<Map<String, String>> resultInfo = ResultInfoUtil.buildError(Constant.ERROR_CODE, ex.getMessage(), "");
        return resultInfo;
    }
}
