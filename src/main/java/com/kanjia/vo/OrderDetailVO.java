package com.kanjia.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单详情vo<br/>
 * fan 2018/8/1 19:05
 */
@Data
@ApiModel(value = "订单详情")
public class OrderDetailVO {

    private Integer uid;

    private Integer aid;    //活动id

    private OrderDetailUserVO orderDetailUserVO;    //用户数据

    private OrderDetailEnterpriseVO orderDetailEnterpriseVO;    //商户数据

    private Integer oid;

    private Integer state;      //状态码

    private String stateName;   //状态名

    private String picture; //活动图片

    private String title;   //活动标题

    private BigDecimal currentPrice;    //当前价格

    private BigDecimal originPrice;     //原价

    private BigDecimal minuPrice;       //最低价

    private Integer limitNumber;    //限量份数

    private Integer soldNumber;     //已售份数

    @ApiModelProperty("活动上架时间")
    private Date putawayTime;

    private Date soldOutTime;    //活动下架时间

    private Date activityTime;      //活动开始时间

    private Date cutTime;           //活动截止时间

    private Date createTime;        //订单创建时间

    private String qrCode;          //订单二维码

    @ApiModelProperty("订单编号")
    private String orderNumber;     //订单编号

    private List<OrderHelperVO> orderHelperVOs; //pick榜数据
}