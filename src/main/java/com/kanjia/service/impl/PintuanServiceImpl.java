package com.kanjia.service.impl;

import com.kanjia.basic.*;
import com.kanjia.dto.PintuanInfoDto;
import com.kanjia.mapper.*;
import com.kanjia.pojo.Activity;
import com.kanjia.pojo.Pintuan;
import com.kanjia.pojo.PintuanUserOrder;
import com.kanjia.pojo.UserOrder;
import com.kanjia.service.PintuanService;
import com.kanjia.utils.ReflectUtil;
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
public class PintuanServiceImpl extends AbstractBaseServiceImpl<Pintuan> implements PintuanService,Observable<PintuanUserOrder> {

    @Autowired
    private PintuanMapper pintuanMapper;
    @Autowired
    private PintuanUserOrderMapper pintuanUserOrderMapper;
    @Autowired
    private UserOrderMapper userOrderMapper;
    @Autowired
    private ActivityMapper activityMapper;

    /**
     * 我这个被观察者需要自己管理观察者,为了保证线程安全，所以用vector作为容器
     */
    private Vector<PintuanUserOrder> observers = new Vector<>();

    @Override
    public BaseMapper<Pintuan> getDao() {
        return pintuanMapper;
    }


    @Override
    public ReturnMessage getPintuanId(Integer oid) {
        Integer pintuanId = pintuanMapper.getPintuanId(oid);
        return new ReturnMessage(ResponseCode.OK, pintuanId);
    }

    @Override
    public ReturnMessage insertOrderToPintuan(Integer tuanid, Integer oid) {
        Pintuan pintuan = pintuanMapper.selectByPrimaryKey(tuanid);
        if(pintuan == null){
            return new ReturnMessage(ResponseCode.PARAM_ERROR, "拼团不存在");
        }
        UserOrder userOrder = userOrderMapper.selectByPrimaryKey(oid);
        if(userOrder == null){
            return new ReturnMessage(ResponseCode.PARAM_ERROR, "订单不存在");
        }
        //判断拼团是否已经超过最大人数了，超过的话将无法再插入，目前默认是3人团
        PintuanUserOrder tmpPintuanUserOrder = new PintuanUserOrder();
        tmpPintuanUserOrder.setPintuanId(tuanid);
        tmpPintuanUserOrder.setState(1);
        List<PintuanUserOrder> select = pintuanUserOrderMapper.select(ReflectUtil.generalMap(tmpPintuanUserOrder));
        for(PintuanUserOrder pintuanUserOrder: select){
            addObserver(pintuanUserOrder);
        }

        Integer orderCount = pintuanMapper.getOrderCount(tuanid);
        if(select.size() >= 3){
            return new ReturnMessage(ResponseCode.SERVICE_NOT_ALLOWED, "该团已达最大拼团人数，无法再拼团");
        }

        //判断活动是否为拼团活动，不为拼团活动无法参与拼团。
        Activity activity = activityMapper.selectByPrimaryKey(userOrder.getActivityId());
        if(activity.getTypes() != Const.ACTIVITY_TYPE_PIN_TUAN){
            return new ReturnMessage(ResponseCode.SERVICE_NOT_ALLOWED, "改订单不为拼团订单，无法参团");
        }

        // 订单是否处于正在进行中的状态，不处于此状态的订单无法插入
        if(userOrder.getState() != Const.ORDER_STATUS_ENGAGING){
            return new ReturnMessage(ResponseCode.SERVICE_NOT_ALLOWED, "该订单还未支付，无法参团");
        }

        //可以插入该订单
        PintuanUserOrder pintuanUserOrder = new PintuanUserOrder();
        pintuanUserOrder.setOrderId(oid);
        pintuanUserOrder.setPintuanId(tuanid);
        pintuanUserOrderMapper.insertSelective(pintuanUserOrder);

        //插入成功后，需要判断状态是否需要改变,如果达到拼团人数，则更新状态
        if(++orderCount >= 3){
            Pintuan tmpTuan = new Pintuan();
            tmpTuan.setId(tuanid);
            tmpTuan.setState(Const.PIN_TUAN_STATUS_FINISH);
            updateByPrimaryKeySelective(tmpTuan);

        }

        return new ReturnMessage(ResponseCode.OK, "插入成功");
    }

    @Override
    public ReturnMessage getPintuanInfo(Integer tuanid) {
        //先获取拼团用户数据
        PintuanInfoVO pintuanInfoVO = new PintuanInfoVO();
        List<OrderUserVO> pintuanUser = pintuanMapper.getPintuanUser(tuanid);
        pintuanInfoVO.setOrderUserVO(pintuanUser);
        PintuanInfoDto pintuanInfo = pintuanMapper.getPintuanInfo(tuanid);

        OrderPintuanActivityVO orderPintuanActivityVO = new OrderPintuanActivityVO();
        pintuanInfoVO.setOrderPintuanActivityVO(orderPintuanActivityVO);

        OrderDetailEnterpriseVO orderDetailEnterpriseVO = new OrderDetailEnterpriseVO();
        pintuanInfoVO.setOrderDetailEnterpriseVO(orderDetailEnterpriseVO);
        return null;
    }

    @Override
    @Transactional  //这里重写了框架中的更新方法，所以需要重新添加事务管理
    public int updateByPrimaryKeySelective(Pintuan record) {
        notifyObserver();
        return super.updateByPrimaryKeySelective(record);
    }

    @Override
    public boolean addObserver(PintuanUserOrder observer) {
        return observers.add(observer);
    }

    @Override
    public boolean removeObserver(PintuanUserOrder observer) {
        return observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for(PintuanUserOrder pintuanUserOrder: observers){
            //为每个观察者开启线程，来执行更新操作
            new Thread(()->{
                Integer orderId = pintuanUserOrder.getOrderId();
                pintuanUserOrder.update(userOrderMapper, orderId);
                observers.remove(pintuanUserOrder);
            }).start();

        }
    }
}