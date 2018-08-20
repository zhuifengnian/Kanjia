package com.kanjia.vo.ordervo;

import lombok.Data;

/**
 * 订单不允许支付时的vo<br/>
 * fan 2018/8/20 20:10
 */
@Data
public class OrderNotAllowedPayVO {
    private String msg;
    private Integer oid;
}