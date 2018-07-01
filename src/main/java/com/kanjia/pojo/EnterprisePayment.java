package com.kanjia.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class EnterprisePayment {
    private Integer id;

    private Integer enterpriseId;

    private BigDecimal totalMoney;

    private BigDecimal withdrawMoney;

    private Date createTime;

    private Date updateTime;

    public EnterprisePayment(Integer id, Integer enterpriseId, BigDecimal totalMoney, BigDecimal withdrawMoney, Date createTime, Date updateTime) {
        this.id = id;
        this.enterpriseId = enterpriseId;
        this.totalMoney = totalMoney;
        this.withdrawMoney = withdrawMoney;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public EnterprisePayment() {
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

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getWithdrawMoney() {
        return withdrawMoney;
    }

    public void setWithdrawMoney(BigDecimal withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
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
}