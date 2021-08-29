package cn.linjk.jarvis.product.repo;

import cn.linjk.jarvis.common.entity.SkillGood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: SkillGoodRepository
 * @author: linjk
 * @date: 2021/8/29 下午11:15
 * @description:
 */
@Repository
public interface SkillGoodRepository extends JpaRepository<SkillGood,Long> {
    @Query(value="select * from skill_goods where status=1 and num>0 and stock_count>0 and id not in (?1)",
            nativeQuery = true)
    List<SkillGood> findSkill(List<Long> ids);

    @Query(value="select * from skill_goods where status=1 and num>0 and stock_count>0",
            nativeQuery = true)
    List<SkillGood> findSkillAll();
}
