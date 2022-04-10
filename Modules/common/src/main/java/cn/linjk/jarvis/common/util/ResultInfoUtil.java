package cn.linjk.jarvis.common.util;

import cn.linjk.jarvis.common.bean.Constant;
import cn.linjk.jarvis.common.bean.ResultInfo;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: ResultInfoUtil
 * @author: linjk
 * @date: 2022/4/10 下午6:41
 * @description:
 */
public class ResultInfoUtil {
    /**
     * 请求出错返回
     *
     * @param path 请求路径
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> buildError(String path) {
        return build(Constant.ERROR_CODE, Constant.ERROR_MESSAGE, path, null);
    }

    /**
     * 请求出错返回
     *
     * @param errorCode 错误代码
     * @param message   错误提示信息
     * @param path      请求路径
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> buildError(int errorCode, String message, String path) {
        return build(errorCode, message, path, null);
    }

    /**
     * 请求成功返回
     *
     * @param path 请求路径
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> buildSuccess(String path) {
        return build(Constant.SUCCESS_CODE, Constant.SUCCESS_MESSAGE, path, null);
    }

    /**
     * 请求成功返回
     *
     * @param path 请求路径
     * @param data 返回数据对象
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> buildSuccess(String path, T data) {
        return build(Constant.SUCCESS_CODE, Constant.SUCCESS_MESSAGE, path, data);
    }

    /**
     * 构建返回对象方法
     *
     * @param code
     * @param message
     * @param path
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> build(Integer code, String message, String path, T data) {
        if (code == null) {
            code = Constant.SUCCESS_CODE;
        }
        if (message == null) {
            message = Constant.SUCCESS_MESSAGE;
        }
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMessage(message);
        resultInfo.setPath(path);
        resultInfo.setData(data);
        return resultInfo;
    }
}
