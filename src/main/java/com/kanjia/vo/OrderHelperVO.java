package com.kanjia.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单帮砍者信息<br/>
 * fan 2018/8/1 22:22
 */
@Data
public class OrderHelperVO {
    private String nickname;

    private String avatarurl;

    private BigDecimal cutPrice;

}