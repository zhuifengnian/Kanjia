package com.kanjia.vo.ordervo;

import com.kanjia.vo.OrderHelperVO;
import lombok.Data;
import java.util.List;

/**
 * 订单中砍价活动vo<br/>
 * fan 2018/8/19 15:34
 */
@Data
public class OrderKanjiaActivityVO extends OrderActivityVO{

    private List<OrderHelperVO> orderHelperVOs; //pick榜数据
}