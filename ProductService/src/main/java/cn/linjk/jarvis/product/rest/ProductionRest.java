package cn.linjk.jarvis.product.rest;

import cn.linjk.jarvis.common.entity.SkillGood;
import cn.linjk.jarvis.product.service.SkillGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: ProductionRest
 * @author: linjk
 * @date: 2021/8/29 下午11:58
 * @description:
 */
@RestController
public class ProductionRest {
    @Autowired
    private SkillGoodService skillGoodService;

    @GetMapping("/product/{productId}")
    @ResponseBody
    public SkillGood getProduct(@PathVariable Long productId){
        System.out.println("调用商品服务");
        SkillGood skillGood=skillGoodService.queryProduct(productId);
        return skillGood;
    }
    @PostMapping("/product")
    public String update(@RequestBody SkillGood skillGood){
        System.out.println("更新库存");
        skillGoodService.update(skillGood);
        return "更新库存成功";
    }
}
