package com.hniu.collection.gtm;

/**
 * 商品类型实体类
 */
public class GoodsType {
    private String id;       // 类型编号
    private String name;     // 类型名称
    private String description; // 描述

    public GoodsType() {}

    public GoodsType(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getter和Setter方法
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }
}