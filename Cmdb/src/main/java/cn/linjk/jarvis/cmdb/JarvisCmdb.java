package cn.linjk.jarvis.cmdb;

import com.alibaba.nacos.api.cmdb.pojo.Entity;
import com.alibaba.nacos.api.cmdb.pojo.EntityEvent;
import com.alibaba.nacos.api.cmdb.pojo.Label;
import com.alibaba.nacos.api.cmdb.pojo.PreservedEntityTypes;
import com.alibaba.nacos.api.cmdb.spi.CmdbService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: JarvisCmdb
 * @author: linjk
 * @date: 2022/4/23 下午8:54
 * @description:
 */
public class JarvisCmdb implements CmdbService {
    private Map<String, Map<String, Entity>> entityMap = new ConcurrentHashMap<>();
    private Map<String, Label> labelMap = new ConcurrentHashMap<>();

    public JarvisCmdb() {
        // 这里写死值，应该从配置库获取values的配置
        Label label = new Label();
        label.setName("cluster");
        Set<String> values = new HashSet<>();
        values.add("BEIJING");
        values.add("GUANGZHOU");
        label.setValues(values);
        labelMap.put(label.getName(), label);
        entityMap.put(PreservedEntityTypes.ip.name(), new HashMap<String, Entity>());
        Entity entity = new Entity();
        entity.setName("127.0.0.1");
        entity.setType(PreservedEntityTypes.ip.name());
        Map<String, String> labels = new HashMap<>();
        labels.put("cluster", "BEIJING");
        entity.setLabels(labels);
        entityMap.get(PreservedEntityTypes.ip.name()).put(entity.getName(), entity);

        entity = new Entity();
        entity.setName("192.168.6.121");
        entity.setType(PreservedEntityTypes.ip.name());
        labels = new HashMap<>();
        labels.put("cluster", "GUANGZHOU ");
        entity.setLabels(labels);
        entityMap.get(PreservedEntityTypes.ip.name()).put(entity.getName(), entity);
    }

    @Override
    public Set<String> getLabelNames() {
        Set<String> set = new HashSet<>();
        set.add("cluster");
        return set;
    }

    @Override
    public Set<String> getEntityTypes() {
        Set<String> set = new HashSet<>();
        set.add(PreservedEntityTypes.ip.name());
        return set;
    }

    @Override
    public Label getLabel(String labelName) {
        return labelMap.get(labelName);
    }

    @Override
    public String getLabelValue(String entityName, String entityType, String labelName) {
        return entityMap.get(entityType).get(entityName).getLabels().get(labelName);
    }

    @Override
    public Map<String, String> getLabelValues(String entityName, String entityType) {
        return entityMap.get(entityType).get(entityName).getLabels();
    }

    @Override
    public Map<String, Map<String, Entity>> getAllEntities() {
        return entityMap;
    }

    @Override
    public List<EntityEvent> getEntityEvents(long l) {
        return null;
    }

    @Override
    public Entity getEntity(String entityName, String entityType) {
        return entityMap.get(entityType).get(entityName);
    }
}
