package cn.linjk.jarvis.common.exception;

import cn.linjk.jarvis.common.bean.Constant;
import cn.linjk.jarvis.common.bean.ResultInfo;
import cn.linjk.jarvis.common.util.ResultInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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

    /**
     * validator异常返回封装
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultInfo<Map<String, String>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest req) {
        String msgs = this.handle(e.getBindingResult().getFieldErrors());
        ResultInfo<Map<String, String>> resultInfo = ResultInfoUtil.buildError(400, msgs, "");
        log.info("参数校验异常异常：{}", e);
        return resultInfo;
    }

    private String handle(List<FieldError> fieldErrors) {
        StringBuilder builder  = new StringBuilder();
        for (FieldError obj : fieldErrors) {
            builder.append(obj.getField());
            builder.append("=[");
            builder.append(obj.getDefaultMessage());
            builder.append("] ");
        }

        return builder.toString();
    }

    /**
     * Assert异常返回封装
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResultInfo<Map<String, String>> handlerIllegalArgumentException(IllegalArgumentException e, HttpServletRequest req) {
        ResultInfo<Map<String, String>> resultInfo = ResultInfoUtil.buildError(500, e.getMessage(), "");
        log.info("参数校验异常异常：{}", e);
        return resultInfo;
    }
}
