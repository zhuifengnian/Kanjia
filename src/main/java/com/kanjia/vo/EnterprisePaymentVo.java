package com.kanjia.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liyue on 2018/6/29
 */
public class EnterprisePaymentVo {
    private Integer id;

    private Integer enterpriseId;

    private BigDecimal minuPrice;

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


    public BigDecimal getMinuPrice() {
        return minuPrice;
    }

    public void setMinuPrice(BigDecimal minuPrice) {
        this.minuPrice = minuPrice;
    }
}
