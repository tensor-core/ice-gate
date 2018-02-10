package com.cssrc.generator.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "storage_info")
public class StorageInfo {
    @Id
    private Integer id;

    /**
     * 产品编号
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 仓库编号
     */
    @Column(name = "warehouse_id")
    private Integer warehouseId;

    /**
     * 状态（入库／出库）
     */
    private String state;

    /**
     * 入库/出库数量
     */
    @Column(name = "warehouse_amount")
    private Integer warehouseAmount;

    /**
     * 入库/出库日期
     */
    @Column(name = "warehouse_date")
    private Date warehouseDate;

    /**
     * 产品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 仓库名称
     */
    @Column(name = "warehouse_name")
    private String warehouseName;

    /**
     * 产品编号
     */
    @Column(name = "product_code")
    private String productCode;

    @Column(name = "crt_time")
    private Date crtTime;

    @Column(name = "crt_user")
    private String crtUser;

    @Column(name = "crt_name")
    private String crtName;

    @Column(name = "crt_host")
    private String crtHost;

    @Column(name = "upd_time")
    private Date updTime;

    @Column(name = "upd_user")
    private String updUser;

    @Column(name = "upd_name")
    private String updName;

    @Column(name = "upd_host")
    private String updHost;

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
     * 获取产品编号
     *
     * @return product_id - 产品编号
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置产品编号
     *
     * @param productId 产品编号
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取仓库编号
     *
     * @return warehouse_id - 仓库编号
     */
    public Integer getWarehouseId() {
        return warehouseId;
    }

    /**
     * 设置仓库编号
     *
     * @param warehouseId 仓库编号
     */
    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * 获取状态（入库／出库）
     *
     * @return state - 状态（入库／出库）
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态（入库／出库）
     *
     * @param state 状态（入库／出库）
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取入库/出库数量
     *
     * @return warehouse_amount - 入库/出库数量
     */
    public Integer getWarehouseAmount() {
        return warehouseAmount;
    }

    /**
     * 设置入库/出库数量
     *
     * @param warehouseAmount 入库/出库数量
     */
    public void setWarehouseAmount(Integer warehouseAmount) {
        this.warehouseAmount = warehouseAmount;
    }

    /**
     * 获取入库/出库日期
     *
     * @return warehouse_date - 入库/出库日期
     */
    public Date getWarehouseDate() {
        return warehouseDate;
    }

    /**
     * 设置入库/出库日期
     *
     * @param warehouseDate 入库/出库日期
     */
    public void setWarehouseDate(Date warehouseDate) {
        this.warehouseDate = warehouseDate;
    }

    /**
     * 获取产品名称
     *
     * @return product_name - 产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置产品名称
     *
     * @param productName 产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取仓库名称
     *
     * @return warehouse_name - 仓库名称
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * 设置仓库名称
     *
     * @param warehouseName 仓库名称
     */
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    /**
     * 获取产品编号
     *
     * @return product_code - 产品编号
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置产品编号
     *
     * @param productCode 产品编号
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return crt_time
     */
    public Date getCrtTime() {
        return crtTime;
    }

    /**
     * @param crtTime
     */
    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * @return crt_user
     */
    public String getCrtUser() {
        return crtUser;
    }

    /**
     * @param crtUser
     */
    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    /**
     * @return crt_name
     */
    public String getCrtName() {
        return crtName;
    }

    /**
     * @param crtName
     */
    public void setCrtName(String crtName) {
        this.crtName = crtName;
    }

    /**
     * @return crt_host
     */
    public String getCrtHost() {
        return crtHost;
    }

    /**
     * @param crtHost
     */
    public void setCrtHost(String crtHost) {
        this.crtHost = crtHost;
    }

    /**
     * @return upd_time
     */
    public Date getUpdTime() {
        return updTime;
    }

    /**
     * @param updTime
     */
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    /**
     * @return upd_user
     */
    public String getUpdUser() {
        return updUser;
    }

    /**
     * @param updUser
     */
    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }

    /**
     * @return upd_name
     */
    public String getUpdName() {
        return updName;
    }

    /**
     * @param updName
     */
    public void setUpdName(String updName) {
        this.updName = updName;
    }

    /**
     * @return upd_host
     */
    public String getUpdHost() {
        return updHost;
    }

    /**
     * @param updHost
     */
    public void setUpdHost(String updHost) {
        this.updHost = updHost;
    }
}