//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kanjia.basic;

public class Const {
    public static final int OK = 200;               //请求正确
    public static final int PARAM_EROOR = 201;      //参数数据错误
    public static final int PARAM_NOT_COMPAT = 202; //参数类型不兼容
    public static final int SERVER_ERROR = 500;     //服务器内部错误

    //user_order表
    public static final int ORDER_STATUS_CANCEL = 0;    //订单已撤销
    public static final int ORDER_STATUS_ENGAGING = 1;    //订单正在砍价
    public static final int ORDER_STATUS_WAITING_CONCUMUE = 2;    //订单待消费
    public static final int ORDER_STATUS_EXCEED_TIME = 3;    //订单已过期
    public static final int ORDER_STATUS_HAS_CONSUME = 4;    //订单已消费
}
