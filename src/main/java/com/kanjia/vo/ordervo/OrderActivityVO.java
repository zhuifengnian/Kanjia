package com.kanjia.vo.ordervo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单活动抽象类<br/>
 * fan 2018/8/19 16:36
 */
@Data
public abstract class OrderActivityVO {
    private Integer activityType;   //活动类型

    private String picture; //活动图片

    private String title;   //活动标题

    private BigDecimal originPrice;     //原价

    private BigDecimal minuPrice;       //最低价

    private Integer limitNumber;    //限量份数

    private Integer soldNumber;     //已售份数

    private Date activityTime;      //活动开始时间

    private Date cutTime;           //活动截止时间
}