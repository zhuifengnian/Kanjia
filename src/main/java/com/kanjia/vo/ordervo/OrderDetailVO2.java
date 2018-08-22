package com.kanjia.vo.ordervo;

import com.kanjia.vo.OrderDetailEnterpriseVO;
import com.kanjia.vo.OrderUserVO;
import lombok.Data;

/**
 * 活动类型为砍价的订单<br/>
 * fan 2018/8/19 0:47
 */
@Data
public class OrderDetailVO2 {
    private Integer uid;

    private OrderUserVO orderDetailUserVO;    //用户数据

    private Integer aid;    //活动id

    private OrderActivityVO orderActivityVO;

    private OrderDetailEnterpriseVO orderDetailEnterpriseVO;    //商户数据

    private Integer oid;

    private OrderDataVO orderDataVO;  //封装了订单数据

}