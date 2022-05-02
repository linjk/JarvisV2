package cn.linjk.jarvis.device.rest;

import cn.linjk.jarvis.common.bean.ResultInfo;
import cn.linjk.jarvis.common.util.ResultInfoUtil;
import cn.linjk.jarvis.device.service.SendVerifyCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: VerifyCodeRest
 * @author: linjk
 * @date: 2022/5/2 下午1:41
 * @description: 发送验证码
 */
@RestController
public class VerifyCodeRest {
    @Resource private SendVerifyCodeService sendVerifyCodeService;
    @Resource private HttpServletRequest request;
    
    @GetMapping(value = "/send")
    public ResultInfo send(String phone) {
        sendVerifyCodeService.send(phone);

        return ResultInfoUtil.buildSuccess("发送成功", request.getServletPath());
    }
}
