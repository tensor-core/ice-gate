package com.cssrc.product.entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "product_info")
public class ProductInfo {
    @Id
    private Integer id;

    /**
     * 货品编码
     */
    private String goodsid;

    /**
     * 名称规格
     */
    private String name;

    /**
     * 类别编号
     */
    private String goodstypeid;

    /**
     * 计量单位
     */
    private String unit;

    /**
     * 库存数量
     */
    private Integer qty;

    /**
     * 货品条码
     */
    private String goodscode;

    /**
     * 货品简称
     */
    private String shortname;

    /**
     * 描述
     */
    private String description;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取货品编码
     *
     * @return goodsid - 货品编码
     */
    public String getGoodsid() {
        return goodsid;
    }

    /**
     * 设置货品编码
     *
     * @param goodsid 货品编码
     */
    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    /**
     * 获取名称规格
     *
     * @return name - 名称规格
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称规格
     *
     * @param name 名称规格
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取类别编号
     *
     * @return goodstypeid - 类别编号
     */
    public String getGoodstypeid() {
        return goodstypeid;
    }

    /**
     * 设置类别编号
     *
     * @param goodstypeid 类别编号
     */
    public void setGoodstypeid(String goodstypeid) {
        this.goodstypeid = goodstypeid;
    }

    /**
     * 获取计量单位
     *
     * @return unit - 计量单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置计量单位
     *
     * @param unit 计量单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取库存数量
     *
     * @return qty - 库存数量
     */
    public Integer getQty() {
        return qty;
    }

    /**
     * 设置库存数量
     *
     * @param qty 库存数量
     */
    public void setQty(Integer qty) {
        this.qty = qty;
    }

    /**
     * 获取货品条码
     *
     * @return goodscode - 货品条码
     */
    public String getGoodscode() {
        return goodscode;
    }

    /**
     * 设置货品条码
     *
     * @param goodscode 货品条码
     */
    public void setGoodscode(String goodscode) {
        this.goodscode = goodscode;
    }

    /**
     * 获取货品简称
     *
     * @return shortname - 货品简称
     */
    public String getShortname() {
        return shortname;
    }

    /**
     * 设置货品简称
     *
     * @param shortname 货品简称
     */
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
}