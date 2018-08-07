package com.kanjia.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liyue on 2018/6/29
 */
public class OrderInfoVo {

    private String orderNumber;

    private String title;

    private BigDecimal currentPrice;

    private String picture;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
