package com.kanjia.service.impl;

import com.kanjia.basic.*;
import com.kanjia.dto.PintuanInfoDto;
import com.kanjia.mapper.*;
import com.kanjia.pojo.*;
import com.kanjia.service.PintuanService;
import com.kanjia.utils.ReflectUtil;
import com.kanjia.utils.TimeUtil;
import com.kanjia.vo.OrderDetailEnterpriseVO;
import com.kanjia.vo.OrderUserVO;
import com.kanjia.vo.ordervo.OrderPintuanActivityVO;
import com.kanjia.vo.pintuanvo.PintuanInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Vector;

/**
 * 团service<br/>
 * fan 2018/8/21 0:44
 */
@Service
public class PintuanServiceImpl extends AbstractBaseServiceImpl<Pintuan> implements PintuanService{

    @Autowired
    private PintuanMapper pintuanMapper;
    @Autowired
    private PintuanUserOrderMapper pintuanUserOrderMapper;
    @Autowired
    private UserOrderMapper userOrderMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseMapper<Pintuan> getDao() {
        return pintuanMapper;
    }


    @Override
    public ReturnMessage insertTuan(Integer tuanid, Integer uid) {
        Pintuan pintuan = pintuanMapper.selectByPrimaryKey(tuanid);
        if(pintuan == null){
            return new ReturnMessage(ResponseCode.PARAM_ERROR, "拼团不存在");
        }
        User user = userMapper.selectByPrimaryKey(uid);
        if(user == null){
            return new ReturnMessage(ResponseCode.PARAM_ERROR, "用户不存在");
        }
        //判断拼团是否已经超过最大人数了，超过的话将无法再插入，目前默认是3人团
        PintuanUserOrder tmpPintuanUserOrder = new PintuanUserOrder();
        tmpPintuanUserOrder.setPintuanId(tuanid);
        tmpPintuanUserOrder.setState(1);
        List<PintuanUserOrder> select = pintuanUserOrderMapper.select(ReflectUtil.generalMap(tmpPintuanUserOrder));
        //判断该用户是否已经在团中，如果是的话，则无法再参加同一个团
        for(PintuanUserOrder pintuanUserOrder: select){
            Integer orderId = pintuanUserOrder.getOrderId();
            UserOrder userOrder = userOrderMapper.selectByPrimaryKey(orderId);
            Integer userId = userOrder.getUserId();
            if(userId == uid){
                return new ReturnMessage(ResponseCode.SERVICE_NOT_ALLOWED, "用户已经在此团中，无法在加入该团");
            }
        }

        if(select.size() >= 3){
            return new ReturnMessage(ResponseCode.SERVICE_NOT_ALLOWED, "该团已达最大拼团人数，无法再拼团");
        }

        //判断活动是否为拼团活动，不为拼团活动无法参与拼团。
        Integer activityId = pintuan.getActivityId();
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        if(activity.getTypes() != Const.ACTIVITY_TYPE_PIN_TUAN){
            return new ReturnMessage(ResponseCode.SERVICE_NOT_ALLOWED, "该订单不为拼团订单，无法参团");
        }

        // 订单是否处于正在进行中的状态，不处于此状态的订单无法插入
        if(pintuan.getState() != Const.PIN_TUAN_STATUS_ENGAGING){
            return new ReturnMessage(ResponseCode.SERVICE_NOT_ALLOWED, "该团不处于正在进行中，无法参团");
        }

        //可以插入该订单，先生成一个订单
        UserOrder userOrder = new UserOrder();
        userOrder.setUserId(uid);
        userOrder.setActivityId(activityId);
        userOrder.setQrCode(TimeUtil.random9Number());  //生成随机9位数二维码
        //生成订单编号,规则为aid加时间戳，加uid
        String orderNumber = "" + activityId + TimeUtil.currentTimeStamp() + uid;
        userOrder.setOrderNumber(orderNumber);
        userOrder.setCurrentPrice(activity.getMinuPrice());
        userOrder.setState(Const.ORDER_STATUS_WAITING_PAY);
        userOrderMapper.insert(userOrder);
        Integer oid = userOrder.getId();

        return new ReturnMessage(ResponseCode.OK, oid);
    }

    @Override
    public ReturnMessage getPintuanId(Integer oid) {
        Integer pintuanId = pintuanMapper.getPintuanId(oid);
        return new ReturnMessage(ResponseCode.OK, pintuanId);
    }


    @Override
    public ReturnMessage getPintuanInfo(Integer tuanid) {

        //先获取拼团用户数据
        PintuanInfoVO pintuanInfoVO = new PintuanInfoVO();
        List<OrderUserVO> pintuanUser = pintuanMapper.getPintuanUser(tuanid);
        pintuanInfoVO.setOrderUserVO(pintuanUser);
        PintuanInfoDto pintuanInfo = pintuanMapper.getPintuanInfo(tuanid);

        OrderPintuanActivityVO orderPintuanActivityVO = new OrderPintuanActivityVO();
        ReflectUtil.copyProperties(orderPintuanActivityVO, pintuanInfo);
        orderPintuanActivityVO.setPintuanSize(3);//TODO:目前只支持三人团
        pintuanInfoVO.setOrderPintuanActivityVO(orderPintuanActivityVO);

        OrderDetailEnterpriseVO orderDetailEnterpriseVO = new OrderDetailEnterpriseVO();
        ReflectUtil.copyProperties(orderDetailEnterpriseVO, pintuanInfo);
        pintuanInfoVO.setOrderDetailEnterpriseVO(orderDetailEnterpriseVO);

        return new ReturnMessage(ResponseCode.OK, pintuanInfoVO);
    }

    @Override
    public ReturnMessage insertTuanFinishPay(Integer tuanid, Integer oid) {
        Pintuan pintuan = pintuanMapper.selectByPrimaryKey(tuanid);
        if (pintuan == null) {
            return new ReturnMessage(ResponseCode.PARAM_ERROR, "拼团不存在");
        }
        UserOrder userOrder = userOrderMapper.selectByPrimaryKey(oid);
        if (userOrder == null) {
            return new ReturnMessage(ResponseCode.PARAM_ERROR, "订单不存在");
        }
        if(userOrder.getState() != Const.ORDER_STATUS_WAITING_PAY){
            return new ReturnMessage(ResponseCode.SERVICE_NOT_ALLOWED, "订单状态不为等待支付，无法支付");
        }
        Activity activity = activityMapper.selectByPrimaryKey(pintuan.getActivityId());
        if(activity.getTypes() != Const.ACTIVITY_TYPE_PIN_TUAN){
            return new ReturnMessage(ResponseCode.SERVICE_NOT_ALLOWED, "不为拼团活动，无法参与拼团");
        }

        //修改订单状态
        UserOrder tmpOrder = new UserOrder();
        tmpOrder.setId(oid);
        tmpOrder.setState(Const.ORDER_STATUS_ENGAGING);
        userOrderMapper.updateByPrimaryKeySelective(tmpOrder);

        //在团订单表中插入
        PintuanUserOrder groupUserOrder = new PintuanUserOrder();
        groupUserOrder.setPintuanId(tuanid);
        groupUserOrder.setOrderId(oid);
        int i = pintuanUserOrderMapper.insertSelective(groupUserOrder);

        //插入成功后，需要判断状态是否需要改变,如果达到拼团人数，则更新状态
        Integer orderCount = pintuanMapper.getOrderCount(tuanid);
        if(orderCount >= 3){
            Pintuan tmpTuan = new Pintuan();
            tmpTuan.setId(tuanid);
            tmpTuan.setState(Const.PIN_TUAN_STATUS_FINISH);

            //更新团下的所有订单状态为待消费
            PintuanUserOrder tmpPintuanUserOrder = new PintuanUserOrder();
            tmpPintuanUserOrder.setPintuanId(tuanid);
            tmpPintuanUserOrder.setState(1);
            List<PintuanUserOrder> select = pintuanUserOrderMapper.select(ReflectUtil.generalMap(tmpPintuanUserOrder));
            select.forEach(pintuanUserOrder -> {
                Integer orderId = pintuanUserOrder.getOrderId();
                new Thread(()->{
                    UserOrder tmpOrder2 = new UserOrder();
                    tmpOrder2.setId(orderId);
                    tmpOrder2.setState(Const.ORDER_STATUS_WAITING_CONCUMUE);
                }).start();
            });
        }
        return new ReturnMessage(ResponseCode.OK, i);
    }
}