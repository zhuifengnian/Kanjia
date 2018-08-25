package com.kanjia.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 拼团信息，采用dto的好处是可以减少数据库的查询次数<br/>
 * fan 2018/8/22 11:08
 */
@Data
public class PintuanInfoDto {
    private Integer types;   //活动类型

    private String picture; //活动图片

    private String title;   //活动标题

    private BigDecimal originPrice;     //原价

    private BigDecimal minuPrice;       //最低价

    private Integer limitNumber;    //限量份数

    private Integer soldNumber;     //已售份数

    private Date activityTime;      //活动开始时间

    private Date cutTime;           //活动截止时间

    private Integer pintuanSize;    //团的大小

    private String address; //活动地址

    private Float latitude;//纬度

    private Float longitude;    //经度

    private String phone;   //商家电话

    private String enterpriseName;  //商家名称

    private String avatarurl;   //商家头像
}