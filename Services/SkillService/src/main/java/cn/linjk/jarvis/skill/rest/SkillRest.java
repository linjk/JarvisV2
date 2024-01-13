package cn.linjk.jarvis.skill.rest;

import cn.linjk.jarvis.skill.service.SkillGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: SkillRest
 * @author: linjk
 * @date: 2021/8/30 上午12:03
 * @description:
 */
@RestController
public class SkillRest {
    @Autowired
    private SkillGoodService skillGoodService;

    @GetMapping("/skill")
    public String add(Long productId,String userId) {
        try{
            skillGoodService.add(productId,userId);
            return "抢单成功";
        }
        catch (Exception e){
            return "商品已经抢光";
        }
    }
}
