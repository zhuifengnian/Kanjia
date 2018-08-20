package com.kanjia.utils;

/**
 * 订单工具类<br/>
 * fan 2018/8/19 20:45
 */
public class OrderUtils {
    public static String getStateName(Integer state){
        String stateName;
        switch (state){
            case 0:
                stateName = "已撤销";
                break;
            case 1:
                stateName = "正在砍价";
                break;
            case 2:
                stateName = "待消费";
                break;
            case 3:
                stateName = "已过期";
                break;
            case 4:
                stateName = "已消费";
                break;
            case 5:
                stateName = "待付款";
                break;
            default:
                stateName = "未知状态";
        }
        

        return stateName;
    }
}