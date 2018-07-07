package com.kanjia.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by liyue on 2018/6/29
 */
@Getter
@Setter
@ToString
public class DetailActivityPriceVo {
    private Integer id;

    private String title;

    private List<String> picture;

    private String video;

    private String adress;

    private BigDecimal originPrice;

    private BigDecimal nowPrice;

    private Date activityTime;

    private Integer enteprirseId;

    private String enterpriseName;

    private String enterprisePhone;

    private Integer limitNumber;

    private Integer soldNumber;

    private Date cutTime;

    private String longitude;

    private String latitude;

    private String description;

}
