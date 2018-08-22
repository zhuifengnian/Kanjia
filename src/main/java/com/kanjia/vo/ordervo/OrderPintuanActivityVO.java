package com.kanjia.vo.ordervo;

import lombok.Data;

/**
 * 订单中的拼团活动<br/>
 * fan 2018/8/20 15:30
 */
@Data
public class OrderPintuanActivityVO extends OrderActivityVO{
    private Integer pintuanSize;    //团的大小

    private String address; //活动地址

    private Float latitude;//纬度

    private Float longitude;    //经度

    private String phone;   //电话
}