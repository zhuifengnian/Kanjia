package com.kanjia.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by luyue on 2018/6/29
 */
@Getter
@Setter
@ToString
public class PageActivityPriceVo {
    private Integer id;

    private String title;

    private String picture;

    private String address;

    private BigDecimal originPrice;

    private BigDecimal minuPrice;

    private Date activityTime;

    private String enterpriseName;

    private Integer limitNumber;

    private Integer soldNumber;

    private Date cutTime;

    private List<String> headSculpture;

}
