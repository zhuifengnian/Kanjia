package com.kanjia.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单详情vo<br/>
 * fan 2018/8/1 19:05
 */
@Data
public class OrderDetailVO {

    private Integer uid;

    private OrderDetailUserVO orderDetailUserVO;    //用户数据

    private Integer oid;

    private String stateName;   //状态名

    private String picture; //活动图片

    private String title;   //活动标题

    private BigDecimal currentPrice;    //当前价格

    private BigDecimal originPrice;     //原价

    private BigDecimal minuPrice;       //最低价

    private Integer limitNumber;    //限量份数

    private Integer soldNumber;     //已售份数

    private Date consumeTime;       //消费（砍价）开始时间

    private Date consumeEndTime;    //消费（砍价）截止时间

    private Date activityTime;      //活动开始时间

    private Date cutTime;           //活动截止时间

    private Date createTime;        //订单创建时间

    private String qrCode;          //订单二维码

    private List<OrderHelperVO> orderHelperVOs; //pick榜数据
}