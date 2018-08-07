package com.kanjia.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Created by liyue on 2018/6/29
 */
public class EnterprisePaymentPriceVo {


    private BigDecimal totalMoney;

    private BigDecimal withdrawMoney;

    private   BigDecimal remainMoney;

    private BigDecimal monthMoney;

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

    public BigDecimal getRemainMoney() {
        return remainMoney;
    }

    public void setRemainMoney(BigDecimal remainMoney) {
        this.remainMoney = remainMoney;
    }

    public BigDecimal getMonthMoney() {
        return monthMoney;
    }

    public void setMonthMoney(BigDecimal monthMoney) {
        this.monthMoney = monthMoney;
    }
}
