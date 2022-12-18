package cn.linjk.jarvis.common.bean;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: Constant
 * @author: linjk
 * @date: 2022/4/10 下午6:25
 * @description:
 */
public class Constant {
    private Constant() {}

    /**
     * 成功
     */
    public static final int SUCCESS_CODE = 1;
    /**
     * 成功提示信息
     */
    public static final String SUCCESS_MESSAGE = "Successful.";
    /**
     * 错误
     */
    public static final int ERROR_CODE = 0;
    /**
     * 未登录
     */
    public static final int NO_LOGIN_CODE = -100;
    /**
     * 请登录提示信息
     */
    public static final String NO_LOGIN_MESSAGE = "Please login.";
    /**
     * 参数错误信息
     */
    public static final String PARAMS_INVALID_MESSAGE = "Params invalid";
    /**
     * 错误提示信息
     */
    public static final String ERROR_MESSAGE = "Oops! Something was wrong.";
}
