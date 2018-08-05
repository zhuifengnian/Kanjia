package com.kanjia.service;

import com.kanjia.basic.ReturnMessage;
import com.kanjia.pojo.HelpUser;
import com.kanjia.pojo.User;
import com.kanjia.pojo.UserOrder;
import com.kanjia.vo.OrderHelperVO;

import java.util.List;

/**
 * 帮助者service<br/>
 * fan 2018/8/5 8:19
 */
public interface HelperService extends BaseService<HelpUser>{
    /**
     * 获取订单所有的帮助者数据
     */
    List<OrderHelperVO> listOrderHelpers(Integer oid);

    /**
     * 帮助者帮助砍价
     * @param order
     * @param user
     * @return
     */
    ReturnMessage helpKanjia(UserOrder order, User user);
}