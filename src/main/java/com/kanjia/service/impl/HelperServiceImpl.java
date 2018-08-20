package com.kanjia.service.impl;

import com.kanjia.basic.Const;
import com.kanjia.basic.ResponseCode;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.mapper.ActivityMapper;
import com.kanjia.mapper.BaseMapper;
import com.kanjia.mapper.HelpUserMapper;
import com.kanjia.mapper.UserOrderMapper;
import com.kanjia.pojo.Activity;
import com.kanjia.pojo.HelpUser;
import com.kanjia.pojo.User;
import com.kanjia.pojo.UserOrder;
import com.kanjia.service.HelperService;
import com.kanjia.vo.OrderHelperVO;
import com.kanjia.wxpay.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 * <br/>
 * fan 2018/8/5 8:21
 */
@Service
public class HelperServiceImpl extends AbstractBaseServiceImpl<HelpUser> implements HelperService {
    @Autowired
    private HelpUserMapper helpUserMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private UserOrderMapper userOrderMapper;
    @Override
    public BaseMapper<HelpUser> getDao() {
        return helpUserMapper;
    }

    @Override
    public List<OrderHelperVO> listOrderHelpers(Integer oid) {
        return helpUserMapper.getOrderHelpers(oid, null);
    }

    @Override
    public ReturnMessage helpKanjia(UserOrder order, User user) {
        //只有当订单处于正在砍价时，才可以砍价
        Integer state = order.getState();
        if(Const.ORDER_STATUS_ENGAGING != state){
            return new ReturnMessage(ResponseCode.SERVICE_NOT_ALLOWED, "订单状态不处于砍价阶段，无法进行砍价操作");
        }
        //判断当前用户是否已经帮助此订单砍过价，砍过则不能继续砍价
        List<OrderHelperVO> orderHelpers = helpUserMapper.getOrderHelpers(order.getId(), null);
        for(OrderHelperVO orderHelperVO : orderHelpers){
            if (orderHelperVO.getUid().equals(user.getId())) {
                return new ReturnMessage(ResponseCode.SERVICE_NOT_ALLOWED, "此帮助者已经为订单砍过价，无法再继续帮砍");
            }
        }
        //可以开始帮砍
        UserOrder tmpUserOrder = new UserOrder();//记录改变的部分
        tmpUserOrder.setId(order.getId());
        //TODO:随机生成砍价金额，暂定在5元之内
        BigDecimal currentPrice = order.getCurrentPrice();
        Activity activity = activityMapper.selectByPrimaryKey(order.getActivityId());
        BigDecimal minuPrice = activity.getMinuPrice(); //能砍到的最低价
        //判断当前价格与最低价的比值,当大于5元时，随机生成一个5元之内的值相减，否则减去期间的差价，并且将订单状态改为预付款
        double subtractRet = currentPrice.subtract(minuPrice).doubleValue();
        BigDecimal shouldSubVal = null;
        if (subtractRet > 5) {
            Random random = new Random();
            double randomVal = (1 - random.nextDouble() * 0.8) * 5; //(0.2-1) * 5
            shouldSubVal = new BigDecimal(randomVal);

        }else{
            shouldSubVal = new BigDecimal(subtractRet);
            tmpUserOrder.setState(Const.ORDER_STATUS_WAITING_PAY);//订单进入预付款状态
        }
        shouldSubVal = shouldSubVal.setScale(2, BigDecimal.ROUND_HALF_UP);//保留两位并4舍5入
        //改变订单的当前价格
        tmpUserOrder.setCurrentPrice(order.getCurrentPrice().subtract(shouldSubVal));
        userOrderMapper.updateByPrimaryKeySelective(tmpUserOrder);
        //订单的帮砍者中，记录当前帮砍者及金额
        HelpUser helpUser = new HelpUser();
        helpUser.setOrderId(order.getId());
        helpUser.setUserId(user.getId());
        helpUser.setCutPrice(shouldSubVal);
        helpUserMapper.insert(helpUser);

        return new ReturnMessage(ResponseCode.OK, helpUser);
    }
}