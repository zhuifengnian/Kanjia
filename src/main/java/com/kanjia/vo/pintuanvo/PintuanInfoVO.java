package com.kanjia.vo.pintuanvo;

import com.kanjia.vo.OrderDetailEnterpriseVO;
import com.kanjia.vo.OrderUserVO;
import com.kanjia.vo.ordervo.OrderPintuanActivityVO;
import lombok.Data;

import java.util.List;

/**
 * 拼团信息vo<br/>
 * fan 2018/8/21 15:10
 */
@Data
public class PintuanInfoVO {
    private List<OrderUserVO> orderUserVO;  //拼团用户

    private OrderPintuanActivityVO orderPintuanActivityVO;//拼团活动vo

    private OrderDetailEnterpriseVO orderDetailEnterpriseVO;//商家信息

}