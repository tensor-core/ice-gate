package com.cssrc.common.MessageInfo;

import java.util.Date;

/**
 * 用于微服务之间的消息传递
 */
public class Message {

    //产品编号
    private Integer productId;

    //产品名称
    private String productName;

    //入库数量
    private Integer qty;

    //入库时间
    private Date createAt;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
