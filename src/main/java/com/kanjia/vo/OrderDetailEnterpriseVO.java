package com.kanjia.vo;

import lombok.Data;

import java.util.Date;

/**
 * 订单详情商户信息<br/>
 * fan 2018/8/3 20:48
 */
@Data
public class OrderDetailEnterpriseVO {

    private String enterpriseName;

    private String phone;

    private String avatarurl;

    private String address;
}