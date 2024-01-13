package cn.linjk.jarvis.skill.service;

import cn.linjk.jarvis.common.entity.SkillEntity;
import cn.linjk.jarvis.common.entity.SkillGood;
import cn.linjk.jarvis.common.entity.SkillOrder;
import cn.linjk.jarvis.skill.repo.SkillOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: MultiThreadOrder
 * @author: linjk
 * @date: 2021/8/31 下午10:55
 * @description:
 */
@Component
public class MultiThreadOrder {
    public static final String SKILL_GOODS_PHONE = "SKILL_GOODS_PHONE";
    public static final String SKILL_GOODS_LIST = "SKILL_GOODS_LIST";
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SkillOrderRepository skillOrderRepository;
    @Autowired
    private ProductService productService;
    @Async
    public void createOrder() throws Exception {
        System.out.println("开始异步抢单");
        SkillEntity skillEntity = (SkillEntity)redisTemplate.boundListOps(SKILL_GOODS_LIST).rightPop();
        if (skillEntity == null) {
            return;
        }
        SkillGood skillGood = productService.getGoodById(Long.valueOf(skillEntity.getProductId()));
        if (skillGood == null) {
            throw new Exception("商品已经被抢光拉");
        }
        if (skillGood.getStockCount() > 0) {
            SkillOrder skillOrder = new SkillOrder();
            skillOrder.setMoney(skillGood.getCostPrice());
            skillOrder.setPayTime(new Date());
            skillOrder.setStatus("0");
            skillOrder.setUserId(skillEntity.getUserId());
            skillOrder.setCreateTime(new Date());
            skillOrder.setSkillId(Long.valueOf(skillEntity.getProductId()));
            skillOrderRepository.save(skillOrder);
            skillGood.setStockCount(skillGood.getStockCount() - 1);
            redisTemplate.boundHashOps(SKILL_GOODS_PHONE).put(skillGood.getId(), skillGood);
            System.out.println("成功秒杀 剩余库存："+skillGood.getStockCount());
        }
        if (skillGood.getStockCount() <= 0) {
            System.out.println("库存已经是负数了:" + skillGood.getStockCount());
            redisTemplate.boundHashOps(SKILL_GOODS_PHONE).delete(skillGood.getId());
            productService.update(skillGood);
        }
        System.out.println("结束异步抢单");
    }
}
