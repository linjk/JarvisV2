package cn.linjk.jarvis.skill.repo;

import cn.linjk.jarvis.common.entity.SkillOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: SkillOrderRepository
 * @author: linjk
 * @date: 2021/8/29 下午11:41
 * @description:
 */
@Repository
public interface SkillOrderRepository extends JpaRepository<SkillOrder,Long> {
}


