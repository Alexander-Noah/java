package com.hniu.collection.gtm;

/**
 * 商品类型实体类
 * 用于表示系统中的商品分类信息
 */
public class GoodsType {
    // 成员变量 ==============================================

    /**
     * 类型编号 - 商品类型的唯一标识
     */
    private String id;

    /**
     * 类型名称 - 商品分类的名称（如"家电"、"食品"等）
     */
    private String name;

    /**
     * 描述 - 对该商品类型的详细说明
     */
    private String description;

    // 构造方法 ==============================================

    /**
     * 默认无参构造方法
     */
    public GoodsType() {
        // 初始化空对象
    }

    /**
     * 全参数构造方法
     * @param id 类型编号
     * @param name 类型名称
     * @param description 类型描述
     */
    public GoodsType(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getter和Setter方法 ====================================

    /**
     * 获取类型编号
     * @return 当前对象的类型编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置类型编号
     * @param id 要设置的类型编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取类型名称
     * @return 当前对象的类型名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置类型名称
     * @param name 要设置的类型名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取类型描述
     * @return 当前对象的描述信息
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置类型描述
     * @param description 要设置的描述信息
     */
    public void setDescription(String description) {
        this.description = description;
    }

    // 重写方法 ==============================================

    /**
     * 重写toString方法
     * @return 返回类型名称作为字符串表示
     * @apiNote 主要用于在列表、下拉框等UI组件中显示
     */
    @Override
    public String toString() {
        return name;
    }
}