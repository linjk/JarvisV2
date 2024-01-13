package cn.linjk.jarvis.skill.service;

import cn.linjk.jarvis.common.entity.SkillGood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: ProductService
 * @author: linjk
 * @date: 2021/8/29 下午11:55
 * @description:
 */
@Service
public class ProductService {
    @Autowired private RestTemplate restTemplate;

    public SkillGood getGoodById(Long productId) {
        return restTemplate.getForObject("http://product-service/product/" + productId, SkillGood.class);
    }

    public void update(SkillGood skillGood) {
        ResponseEntity<String> result= restTemplate.postForEntity("http://product-service/product/",skillGood,String.class);
        System.out.println(result.getBody());
    }
}

