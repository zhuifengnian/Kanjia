package com.kanjia.service.impl;

import com.kanjia.basic.*;
import com.kanjia.mapper.*;
import com.kanjia.pojo.*;
import com.kanjia.service.EnterpriseBillService;
import com.kanjia.service.EnterprisePaymentService;
import com.kanjia.service.UserOrderService;
import com.kanjia.utils.OrderUtils;
import com.kanjia.utils.ReflectUtil;
import com.kanjia.utils.TimeUtil;
import com.kanjia.vo.*;
import com.kanjia.vo.ordervo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service("userOrderService")
public class UserOrderServiceImpl extends AbstractBaseServiceImpl<UserOrder> implements UserOrderService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserOrderMapper userOrderMapper;
    @Autowired
    private HelpUserMapper helpUserMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private EnterprisePaymentService enterprisePaymentService;
    @Autowired
    private EnterpriseBillService enterpriseBillService;

    @Override
    public BaseMapper<UserOrder> getDao() {
        return userOrderMapper;
    }


    @Override
    public EnterprisePaymentVo getQrCode(String qr_code) {
        return userOrderMapper.getQrCode(qr_code);
    }

    @Override
    public List<EnterpriseOrderVo> getEnterpriseAllOrder(Integer id, Page page) {
        return userOrderMapper.getEnterpriseAllOrder(id, page);
    }

    @Override
    public Integer getEnterpriseAllOrderCount(Integer id) {
        return userOrderMapper.getEnterpriseAllOrderCount(id);
    }

    @Override
    public List<EnterpriseOrderVo> getEnterpriseNowOrder(Integer id, Timestamp d, Timestamp t, Page page) {
        return userOrderMapper.getEnterpriseNowOrder(id, d, t, page);
    }

    @Override
    public Integer getEnterpriseNowOrderCount(Integer id, Timestamp d, Timestamp t) {
        return userOrderMapper.getEnterpriseNowOrderCount(id, d, t);
    }

    @Override
    public List<EnterpriseOrderVo> getEnterpriseFeatureOrder(Integer id, Timestamp d, Page page) {
        return userOrderMapper.getEnterpriseFeatureOrder(id, d, page);
    }

    @Override
    public Integer getEnterpriseFeatureOrderCount(Integer id, Timestamp d) {
        return userOrderMapper.getEnterpriseFeatureOrderCount(id, d);
    }

    @Override
    public List<EnterpriseOrderVo> getEnterpriseDeleteOrder(Integer id, Page page) {
        return userOrderMapper.getEnterpriseDeleteOrder(id, page);
    }

    @Override
    public Integer getEnterpriseDeleteOrderCount(Integer id) {
        return userOrderMapper.getEnterpriseDeleteOrderCount(id);
    }

    @Override
    public List<EnterpriseOrderVo> getEnterpriseOverOrder(Integer id, Page page) {
        return userOrderMapper.getEnterpriseOverOrder(id, page);
    }

    @Override
    public Integer getEnterpriseOverOrderCount(Integer id) {
        return userOrderMapper.getEnterpriseOverOrderCount(id);
    }

    @Override
    public List<EnterpriseOrderVo> getEnterpriseConsumeOrder(Integer id, Page page) {
        return userOrderMapper.getEnterpriseConsumeOrder(id, page);
    }

    @Override
    public Integer getEnterpriseConsumeOrderCount(Integer id) {
        return userOrderMapper.getEnterpriseConsumeOrderCount(id);
    }

    @Override
    public PageInfo<EnterpriseOrderVo> getEnterpriseOrder(String name, Integer id, Timestamp d, Timestamp t, Page page) {
        PageInfo<EnterpriseOrderVo> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(page.getPageNumber());
        pageInfo.setPageSize(page.getPageSize());
//        if ("今日订单".equals(name)) {
//            pageInfo.setRows(getEnterpriseNowOrder(id, d, t, page));
//            pageInfo.setTotal(getEnterpriseNowOrderCount(id, d, t));
//        } else if ("已取消".equals(name)) {
//            pageInfo.setRows(getEnterpriseDeleteOrder(id, page));
//            pageInfo.setTotal(getEnterpriseDeleteOrderCount(id));
//        } else if ("已过期".equals(name)) {
//            pageInfo.setRows(getEnterpriseOverOrder(id, page));
//            pageInfo.setTotal(getEnterpriseOverOrderCount(id));
//        } else i
       if ("全部".equals(name)) {
            pageInfo.setRows(getEnterpriseAllOrder(id, page));
            pageInfo.setTotal(getEnterpriseAllOrderCount(id));

        } else if ("未消费".equals(name)) {
            pageInfo.setRows(getEnterpriseFeatureOrder(id, t, page));
            pageInfo.setTotal(getEnterpriseFeatureOrderCount(id, t));
        } else if ("已消费".equals(name)) {
            pageInfo.setRows(getEnterpriseConsumeOrder(id, page));
            pageInfo.setTotal(getEnterpriseConsumeOrderCount(id));
        }
        return pageInfo;
    }

    @Override
    public List<EnterpriseOrderVo> getEnterpriseMonthOrder(Integer id, Timestamp d, Page page) {
        return userOrderMapper.getEnterpriseMonthOrder(id, d, page);
    }

    @Override
    public Integer getEnterpriseMonthOrderCount(Integer id) {
        return userOrderMapper.getEnterpriseMonthOrderCount(id);
    }

    @Override
    public PageInfo<EnterpriseOrderVo> EnterpriseMonthOrder(Integer id, Timestamp d, Page page) {
        PageInfo<EnterpriseOrderVo> pageInfo = new PageInfo<>();
        List<EnterpriseOrderVo> list = userOrderMapper.getEnterpriseMonthOrder(id, d, page);
        pageInfo.setRows(list);
        pageInfo.setTotal(userOrderMapper.getEnterpriseMonthOrderCount(id));
        return pageInfo;
    }

    @Override
    public PageInfo<UserOrder> getOrdersByAid(Integer aid, Page page) {
        PageInfo<UserOrder> userOrderPageInfo = new PageInfo<>();
        List<UserOrder> userOrders = userOrderMapper.getOrdersByAid(aid, page);
        userOrderPageInfo.setRows(userOrders);
        if (page != null) {
            userOrderPageInfo.setPageNum(page.getPageNumber());
            userOrderPageInfo.setPageSize(page.getPageSize());
        }
        userOrderPageInfo.setTotal(userOrderMapper.getOrdersByAidCount(aid));
        return userOrderPageInfo;
    }

    @Override
    public List<String> getOrdersPicture(Integer aid) {
        return userOrderMapper.getOrdersPicture(aid);
    }

    @Override
    public PageInfo<KanjiaOrderVo> listKanjiaOrders(Integer uid, Page page) {
        PageInfo<KanjiaOrderVo> kanjiaOrderVoPageInfo = new PageInfo<>();
        //从订单中找出指定用户，并且状态为正在砍价的订单
        List<KanjiaOrderVo> kanjiaOrderVos = userOrderMapper.listKanjiaOrders(uid, page);
        //将帮砍者头像加入
        for(KanjiaOrderVo kanjiaOrderVo: kanjiaOrderVos){
            Integer helperNum = kanjiaOrderVo.getHelperNum() > 3 ? 3 : kanjiaOrderVo.getHelperNum();
            List<String> helperAvatars = helpUserMapper.getOrderHelperAvatars(kanjiaOrderVo.getOid(), helperNum);
            kanjiaOrderVo.setHelperAvatars(helperAvatars);
        }
        kanjiaOrderVoPageInfo.setRows(kanjiaOrderVos);
        kanjiaOrderVoPageInfo.setTotal(userOrderMapper.listKanjiaOrdersCount(uid));
        if(page != null){
            kanjiaOrderVoPageInfo.setPageNum(page.getPageNumber());
            kanjiaOrderVoPageInfo.setPageSize(page.getPageSize());
        }
        return  kanjiaOrderVoPageInfo;
    }

    @Override
    public PageInfo<MyOrderVo> listMyOrders(Integer uid, Page page) {
        PageInfo<MyOrderVo> myOrderVoPageInfo = new PageInfo<>();
        List<MyOrderVo> myOrderVos = userOrderMapper.listMyOrders(uid, page);

        //将帮砍者头像加入
        for(MyOrderVo myOrderVo: myOrderVos){
            Integer helperNum = myOrderVo.getHelperNum() > 3 ? 3 : myOrderVo.getHelperNum();
            List<String> helperAvatars = helpUserMapper.getOrderHelperAvatars(myOrderVo.getOid(), helperNum);
            myOrderVo.setHelperAvatars(helperAvatars);
        }
        myOrderVoPageInfo.setRows(myOrderVos);
        myOrderVoPageInfo.setTotal(userOrderMapper.listMyOrdersCount(uid));

        if(page != null){
            myOrderVoPageInfo.setPageNum(page.getPageNumber());
            myOrderVoPageInfo.setPageSize(page.getPageSize());
        }
        return myOrderVoPageInfo;
    }

    @Override
    public ReturnMessage generateOrder(Integer uid, Integer aid) {
        Activity activity = activityMapper.selectByPrimaryKey(aid);
        if(activity == null){
            return new ReturnMessage(ResponseCode.PARAM_ERROR, "所传活动不存在");
        }

        //首先判断用户是否有正在砍价的该商品
        UserOrder userOrder = new UserOrder();
        userOrder.setUserId(uid);
        userOrder.setActivityId(aid);
        if(activity.getTypes() == null || Const.ACTIVITY_TYPE_BUY == activity.getTypes()){
            userOrder.setState(Const.ORDER_STATUS_WAITING_PAY);//直接购买先进入待付款状态
        }else{
            userOrder.setState(Const.ORDER_STATUS_ENGAGING);    //砍价类型活动状进入正在砍价
        }
        List<UserOrder> select = userOrderMapper.select(ReflectUtil.generalMap(userOrder));
        if(select.size() > 0){
            OrderNotAllowedPayVO orderNotAllowedPayVO = new OrderNotAllowedPayVO();
            orderNotAllowedPayVO.setMsg("该商品您有一个订单尚未付款，请先去付款");
            orderNotAllowedPayVO.setOid(select.get(0).getId());
            return new ReturnMessage(ResponseCode.SERVICE_NOT_ALLOWED, orderNotAllowedPayVO);
        }

        UserOrder tmpOrder = new UserOrder();
        tmpOrder.setUserId(uid);
        tmpOrder.setActivityId(aid);

        if(activity.getTypes() == null || Const.ACTIVITY_TYPE_BUY == activity.getTypes() || Const.ACTIVITY_TYPE_PIN_TUAN == activity.getTypes()){
            tmpOrder.setCurrentPrice(activity.getMinuPrice());  //直接购买或拼团的当前价为现价
            tmpOrder.setState(Const.ORDER_STATUS_WAITING_PAY);
        }else{
            tmpOrder.setCurrentPrice(activity.getOriginPrice());//砍价的当前价为原价
            tmpOrder.setState(Const.ORDER_STATUS_ENGAGING);    //初始化订单状态为正在砍价
        }
        tmpOrder.setQrCode(TimeUtil.random9Number());  //生成随机9位数二维码
        //生成订单编号,规则为aid加时间戳，加uid
        String orderNumber = "" + aid + TimeUtil.currentTimeStamp() + uid;
        tmpOrder.setOrderNumber(orderNumber);
        userOrderMapper.insert(tmpOrder);

        return new ReturnMessage(ResponseCode.OK, tmpOrder.getId());
    }

    @Override
    public OrderDetailVO getOrderDetail(Integer oid) {
        OrderDetailVO orderDetailVO = userOrderMapper.getOrderDetail(oid);
        //根据用户id获取用户信息
        User user = userMapper.selectByPrimaryKey(orderDetailVO.getUid());
        OrderDetailUserVO orderDetailUserVO = new OrderDetailUserVO();
        orderDetailUserVO.setUid(user.getId());
        orderDetailUserVO.setNickname(user.getNickname());
        orderDetailUserVO.setAvatarurl(user.getAvatarurl());
        orderDetailVO.setOrderDetailUserVO(orderDetailUserVO);

        //获取订单对应的商户信息
        Activity activity = activityMapper.selectByPrimaryKey(orderDetailVO.getAid());
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(activity.getEnterpriseId());
        OrderDetailEnterpriseVO orderDetailEnterpriseVO = new OrderDetailEnterpriseVO();
        orderDetailEnterpriseVO.setEnterpriseName(enterprise.getEnterpriseName());
        orderDetailEnterpriseVO.setAddress(enterprise.getAddress());
        orderDetailEnterpriseVO.setAvatarurl(enterprise.getAvatarurl());
        orderDetailEnterpriseVO.setPhone(enterprise.getPhone());
        orderDetailVO.setOrderDetailEnterpriseVO(orderDetailEnterpriseVO);

        //帮助者相关信息
        List<OrderHelperVO> orderHelpers = helpUserMapper.getOrderHelpers(oid, 3);
        orderDetailVO.setOrderHelperVOs(orderHelpers);

        return orderDetailVO;
    }

    @Override
    public OrderDetailVO2 getOrderDetail2(Integer oid) {
        OrderDetailVO2 orderDetailVO2 = userOrderMapper.getOrderDetail2(oid);
        //根据用户id获取用户信息
        User user = userMapper.selectByPrimaryKey(orderDetailVO2.getUid());
        OrderDetailUserVO orderDetailUserVO = new OrderDetailUserVO();
        orderDetailUserVO.setUid(user.getId());
        orderDetailUserVO.setNickname(user.getNickname());
        orderDetailUserVO.setAvatarurl(user.getAvatarurl());
        orderDetailVO2.setOrderDetailUserVO(orderDetailUserVO);

        Activity activity = activityMapper.selectByPrimaryKey(orderDetailVO2.getAid());
        //获取订单对应的活动信息
        OrderActivityVO orderActivityVO = null;
        //判断活动的类型
        switch (activity.getTypes()){
            case Const.ACTIVITY_TYPE_BUY:
                //目前直接购买的不需要做特殊处理，将来会涉及优惠券
                orderActivityVO = commonActivityVO(activity);
                break;
            case Const.ACTIVITY_TYPE_KAN_JIA:
                //砍价类型的活动
                OrderKanjiaActivityVO orderKanjiaActivityVO = (OrderKanjiaActivityVO) commonActivityVO(activity);
                //帮助者相关信息
                List<OrderHelperVO> orderHelpers = helpUserMapper.getOrderHelpers(oid, 3);
                orderKanjiaActivityVO.setOrderHelperVOs(orderHelpers);
                orderActivityVO = orderKanjiaActivityVO;
                break;
            case Const.ACTIVITY_TYPE_PIN_TUAN:
                orderActivityVO = commonActivityVO(activity);
                break;
            default:
                orderActivityVO = commonActivityVO(activity);
        }
        orderDetailVO2.setOrderActivityVO(orderActivityVO);

        //获取订单对应的商户信息
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(activity.getEnterpriseId());
        OrderDetailEnterpriseVO orderDetailEnterpriseVO = new OrderDetailEnterpriseVO();
        orderDetailEnterpriseVO.setEnterpriseName(enterprise.getEnterpriseName());
        orderDetailEnterpriseVO.setAddress(enterprise.getAddress());
        orderDetailEnterpriseVO.setAvatarurl(enterprise.getAvatarurl());
        orderDetailEnterpriseVO.setPhone(enterprise.getPhone());
        orderDetailVO2.setOrderDetailEnterpriseVO(orderDetailEnterpriseVO);

        //获取订单信息
        UserOrder userOrder = userOrderMapper.selectByPrimaryKey(oid);
        OrderDataVO orderDataVO = new OrderDataVO();
        orderDataVO.setCreateTime(userOrder.getCreateTime());
        orderDataVO.setQrCode(userOrder.getQrCode());
        orderDataVO.setCurrentPrice(userOrder.getCurrentPrice());
        orderDataVO.setOrderNumber(userOrder.getOrderNumber());
        orderDataVO.setState(userOrder.getState());
        orderDataVO.setStateName(OrderUtils.getStateName(userOrder.getState()));

        //判断订单是否已过期，如果已过期，会将订单状态改变
        if(Calendar.getInstance().getTime().after(activity.getCutTime())){
            UserOrder tmpUserOrder = new UserOrder();
            tmpUserOrder.setId(oid);
            tmpUserOrder.setState(Const.ORDER_STATUS_EXCEED_TIME);
            userOrderMapper.updateByPrimaryKeySelective(tmpUserOrder);
            //重新加载订单状态信息
            orderDataVO.setState(Const.ORDER_STATUS_EXCEED_TIME);
            orderDataVO.setStateName(OrderUtils.getStateName(Const.ORDER_STATUS_EXCEED_TIME));
        }

        orderDetailVO2.setOrderDataVO(orderDataVO);

        return orderDetailVO2;
    }

    private OrderActivityVO commonActivityVO(Activity activity) {
        OrderKanjiaActivityVO orderKanjiaActivityVO = new OrderKanjiaActivityVO();
        orderKanjiaActivityVO.setActivityType(activity.getTypes());
        orderKanjiaActivityVO.setPicture(activity.getPicture());
        orderKanjiaActivityVO.setTitle(activity.getTitle());
        orderKanjiaActivityVO.setOriginPrice(activity.getOriginPrice());
        orderKanjiaActivityVO.setMinuPrice(activity.getMinuPrice());
        orderKanjiaActivityVO.setLimitNumber(activity.getLimitNumber());
        orderKanjiaActivityVO.setSoldNumber(activity.getSoldNumber());
        orderKanjiaActivityVO.setActivityTime(activity.getActivityTime());
        orderKanjiaActivityVO.setCutTime(activity.getCutTime());
        return orderKanjiaActivityVO;
    }

    @Override
    public PageInfo<OrderListVO> listOrders(Integer uid, Integer orderState, Page page) {
        //分页准备
        PageInfo<OrderListVO> pageInfo = new PageInfo<>();
        if(page != null){
            pageInfo.setPageNum(page.getPageNumber());
            pageInfo.setPageSize(page.getPageSize());
        }

        List<OrderListVO> orderVOs = new ArrayList<>();

        switch(orderState){
            //全部
            case Const.ORDER_LIST_STATUS_ALL:
                orderVOs = userOrderMapper.listOrders(uid, Const.ORDER_LIST_STATUS_ALL, page);
                pageInfo.setTotal(userOrderMapper.listOrdersCount(uid, Const.ORDER_LIST_STATUS_ALL));
                break;
            //砍价中
            case Const.ORDER_LIST_STATUS_ENGAGING:
                orderVOs = userOrderMapper.listOrders(uid, Const.ORDER_LIST_STATUS_ENGAGING, page);
                pageInfo.setTotal(userOrderMapper.listOrdersCount(uid, Const.ORDER_LIST_STATUS_ENGAGING));
                break;
            //待消费
            case Const.ORDER_LIST_STATUS_WAIT_CONSUME:
                orderVOs = userOrderMapper.listOrders(uid, Const.ORDER_LIST_STATUS_WAIT_CONSUME, page);
                pageInfo.setTotal(userOrderMapper.listOrdersCount(uid, Const.ORDER_LIST_STATUS_WAIT_CONSUME));
                break;
            //已完成
            case Const.ORDER_LIST_STATUS_FINISH:
                orderVOs = userOrderMapper.listOrders(uid, Const.ORDER_LIST_STATUS_FINISH, page);
                pageInfo.setTotal(userOrderMapper.listOrdersCount(uid, Const.ORDER_LIST_STATUS_FINISH));
                break;
            //退款中
            case Const.ORDER_LIST_STATUS_DRAWBACKING:
                orderVOs = userOrderMapper.listOrders(uid, Const.ORDER_LIST_STATUS_DRAWBACKING, page);
                pageInfo.setTotal(userOrderMapper.listOrdersCount(uid, Const.ORDER_LIST_STATUS_DRAWBACKING));
                break;
        }
        //拿到用户id,再拿到其下指定订单
        dealOrderListVO(orderVOs);
        pageInfo.setRows(orderVOs);

        return pageInfo;
    }

    @Override
    public int[] getCurrentPrice(String order_number) {
        int []insert={-1,-1,-1};
        UserOrder userOrder= userOrderMapper.getCurrentPrice(order_number);
        if(userOrder!=null) {
            userOrder.setState(Const.ORDER_STATUS_HAS_CONSUME);
            userOrder.setUpdateTime(Calendar.getInstance().getTime());
            insert[0] = userOrderMapper.updateByPrimaryKey(userOrder);

            Activity activity = activityMapper.selectByPrimaryKey(userOrder.getActivityId());

            EnterprisePayment enterprisePayment = enterprisePaymentService.getEnterprisePayment(activity.getEnterpriseId());
            if (enterprisePayment.getTotalMoney() != null) {
                enterprisePayment.setTotalMoney(enterprisePayment.getTotalMoney().add(userOrder.getCurrentPrice()));
            } else {
                enterprisePayment.setTotalMoney(userOrder.getCurrentPrice());

            }
            insert[1] = enterprisePaymentService.updateByPrimaryKeySelective(enterprisePayment);

            EnterpriseBill enterpriseBill = new EnterpriseBill();
            enterpriseBill.setCreateTime(Calendar.getInstance().getTime());
            enterpriseBill.setUpdateTime(Calendar.getInstance().getTime());
            enterpriseBill.setEnterpriseId(activity.getEnterpriseId());
            enterpriseBill.setMoney(userOrder.getCurrentPrice());
            enterpriseBill.setOrderId(userOrder.getId());
            enterpriseBill.setTitle(activity.getTitle());
            enterpriseBill.setType("收入");
            String billnumber = String.valueOf(Calendar.getInstance().getTimeInMillis()) + userOrder.getId();
            enterpriseBill.setBillNumber(billnumber);
            insert[2] = enterpriseBillService.insertSelective(enterpriseBill);
        }
        return insert;
    }

    @Override
    public OrderInfoVo getOrderInfo(String qr_code) {
        return userOrderMapper.getOrderInfo(qr_code);
    }

    @Override
    public ReturnMessage deleteOrder(Integer oid) {
        //删除订单即将订单的状态设为不可用
        UserOrder userOrder = userOrderMapper.selectByPrimaryKey(oid);
        if(userOrder == null){
            return new ReturnMessage(ResponseCode.PARAM_ERROR, "所传订单id不存在");
        }
        //修改状态
        UserOrder tmpOrder = new UserOrder();
        tmpOrder.setId(oid);
        tmpOrder.setState(Const.ORDER_STATUS_CANCEL);
        int i = userOrderMapper.updateByPrimaryKeySelective(tmpOrder);
        return new ReturnMessage(ResponseCode.OK, i);
    }

    @Override
    public ReturnMessage finishPay(Integer oid) {
        UserOrder userOrder = userOrderMapper.selectByPrimaryKey(oid);
        if(userOrder == null){
            return new ReturnMessage(ResponseCode.PARAM_ERROR, "所传订单id不存在");
        }
        //判断订单是否处于待付款状态，如果不是，则提示前台不可调用
        if(Const.ORDER_STATUS_WAITING_PAY != userOrder.getState()){
            return new ReturnMessage(ResponseCode.SERVICE_NOT_ALLOWED, "订单状态不为待付款状态，无法修改");
        }
        //开始修改状态
        UserOrder tmpOrder = new UserOrder();
        tmpOrder.setId(oid);
        tmpOrder.setState(Const.ORDER_STATUS_WAITING_CONCUMUE);
        int i = userOrderMapper.updateByPrimaryKeySelective(tmpOrder);
        return new ReturnMessage(ResponseCode.OK, i);
    }

    @Override
    public ReturnMessage stopKanjia(Integer oid) {
        UserOrder userOrder = userOrderMapper.selectByPrimaryKey(oid);
        if(userOrder == null){
            return new ReturnMessage(ResponseCode.PARAM_ERROR, "订单不存在");
        }
        Integer activityId = userOrder.getActivityId();
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        if(activity.getTypes() != Const.ACTIVITY_TYPE_KAN_JIA || userOrder.getState() != Const.ORDER_STATUS_ENGAGING){
            return new ReturnMessage(ResponseCode.SERVICE_NOT_ALLOWED, "不是砍价活动，或订单状态不为砍价，不能调用此接口");
        }
        UserOrder tmpOrder = new UserOrder();
        tmpOrder.setId(oid);
        tmpOrder.setState(Const.ORDER_STATUS_WAITING_PAY);//将状态由正在砍价改为等待支付
        int i = userOrderMapper.updateByPrimaryKeySelective(tmpOrder);
        return new ReturnMessage(ResponseCode.OK, i);
    }

    /**
     * 处理订单列表
     * @param orderVOs
     */
    private void dealOrderListVO(List<OrderListVO> orderVOs) {
        //将帮砍者头像加入
        for(OrderListVO orderVO: orderVOs){
            Integer helperNum = orderVO.getHelperNum() > 3 ? 3 : orderVO.getHelperNum();
            List<String> helperAvatars = helpUserMapper.getOrderHelperAvatars(orderVO.getOid(), helperNum);
            orderVO.setHelperAvatars(helperAvatars);
        }
    }
}
