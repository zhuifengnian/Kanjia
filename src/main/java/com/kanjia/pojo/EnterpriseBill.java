package com.kanjia.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class EnterpriseBill {
    private Integer id;

    private Integer enterpriseId;

    private String type;

    private String title;

    private BigDecimal money;

    private Date createTime;

    private Date updateTime;

    private Integer orderId;

    private String billNumber;

    public EnterpriseBill(Integer id, Integer enterpriseId, String type, String title, BigDecimal money, Date createTime, Date updateTime, Integer orderId, String billNumber) {
        this.id = id;
        this.enterpriseId = enterpriseId;
        this.type = type;
        this.title = title;
        this.money = money;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.orderId = orderId;
        this.billNumber = billNumber;
    }

    public EnterpriseBill() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber == null ? null : billNumber.trim();
    }
}