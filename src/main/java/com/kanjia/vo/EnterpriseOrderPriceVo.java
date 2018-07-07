package com.kanjia.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Created by liyue on 2018/6/29
 */
@Getter
@Setter
@ToString
public class EnterpriseOrderPriceVo {
    private Integer id;

    private Integer userId;

    private BigDecimal currentPrice;

    private String title;

    private String picture;

    private String nickName;


}
