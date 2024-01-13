package cn.linjk.jarvis.skill.service;

import cn.linjk.jarvis.common.entity.SkillEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: SkillGoodService
 * @author: linjk
 * @date: 2021/8/30 上午12:02
 * @description:
 */
@Service
public class SkillGoodService {
    public static final String SKILL_GOODS_LIST = "SKILL_GOODS_LIST";
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MultiThreadOrder multiThreadOrder;

    @Transactional
    public void add(Long productId, String userId) throws Exception {
        SkillEntity skillEntity = new SkillEntity();
        skillEntity.setProductId(productId.toString());
        skillEntity.setUserId(userId);
        redisTemplate.boundListOps(SKILL_GOODS_LIST).leftPush(skillEntity);
        multiThreadOrder.createOrder();
    }
}
