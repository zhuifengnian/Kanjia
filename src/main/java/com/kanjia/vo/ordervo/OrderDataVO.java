package com.kanjia.vo.ordervo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单数据vo<br/>
 * fan 2018/8/19 15:38
 */
@Data
public class OrderDataVO {

    private Date createTime;        //订单创建时间

    private String qrCode;          //订单二维码

    private BigDecimal currentPrice;    //当前价格

    @ApiModelProperty("订单编号")
    private String orderNumber;     //订单编号

    private Integer state;      //状态码

    private String stateName;   //状态名
}