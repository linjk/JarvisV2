package cn.linjk.jarvis.skill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: SkillServiceApplication
 * @author: linjk
 * @date: 2021/8/29 下午10:47
 * @description:
 */
@EnableAsync
@SpringBootApplication
@EntityScan(basePackages = {
        "cn.linjk.jarvis.common"
})
public class SkillServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkillServiceApplication.class, args);
    }
}
