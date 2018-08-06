package com.kanjia.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单列表vo<br/>
 * fan 2018/8/5 23:36
 */
@Data
public class OrderListVO {
    private Integer oid;

    private Integer state;

    private String stateName;

    private String title;

    private String picture;

    private Date activityTime;

    private Date cutTime;   //活动截止时间

    private BigDecimal originPrice;

    private  BigDecimal currentPrice;

    private BigDecimal minuPrice;

    private String qrCode;

    private List<String> helperAvatars;

    private Integer helperNum;

}