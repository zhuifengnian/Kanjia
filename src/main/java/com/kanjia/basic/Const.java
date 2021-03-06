//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kanjia.basic;

public class Const {

    //user_order表
    public static final int ORDER_STATUS_CANCEL = 0;    //订单已撤销
    public static final int ORDER_STATUS_ENGAGING = 1;    //订单正在砍价
    public static final int ORDER_STATUS_WAITING_CONCUMUE = 2;    //订单待消费
    public static final int ORDER_STATUS_EXCEED_TIME = 3;    //订单已过期
    public static final int ORDER_STATUS_HAS_CONSUME = 4;    //订单已消费
    public static final int ORDER_STATUS_WAITING_PAY = 5;    //订单待付款
    public static final int ORDER_STATUS_DRAWBACKING = 6;    //订单退款中

    //订单列表相关，只用于标记，不是数据库中的值（全部，砍价中，待消费，已完成，退款中）
    public static final int ORDER_LIST_STATUS_ALL = 100;     //列出所有状态的订单
    public static final int ORDER_LIST_STATUS_ENGAGING = 101;     //列出砍价中的订单(包括待支付）
    public static final int ORDER_LIST_STATUS_WAIT_CONSUME = 102;     //列出等待消费的订单
    public static final int ORDER_LIST_STATUS_FINISH = 103;     //列出已完成的订单
    public static final int ORDER_LIST_STATUS_DRAWBACKING = 104;     //列出退款中的订单
    
    //活动类型（直接购买，拼团，砍价）
    public static final int ACTIVITY_TYPE_BUY = 1;   //直接购买
    public static final int ACTIVITY_TYPE_PIN_TUAN = 2;   //拼团
    public static final int ACTIVITY_TYPE_KAN_JIA = 3;   //砍价
    //活动状态
    public static final int ACTIVITY_TYPE_GROUP=1; //上架
    public static final int ACTIVITY_TYPE_down=3; //下架
    public static final int ACTIVITY_TYPE_STOCK=2; //售空
    public static final int ACTIVITY_TYPE_DELETE=0; //上架

    //pintuan表状态
    public static final int PIN_TUAN_STATUS_CANCEL = 0; //已撤销
    public static final int PIN_TUAN_STATUS_ENGAGING = 1; //拼团中
    public static final int PIN_TUAN_STATUS_FINISH = 2; //已完成
    public static final int PIN_TUAN_STATUS_EXCEED_TIME = 0; //已过期

}
