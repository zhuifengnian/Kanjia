package com.kanjia.service;


import com.kanjia.basic.Page;
import com.kanjia.basic.PageInfo;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.pojo.UserOrder;
import com.kanjia.vo.*;
import com.kanjia.vo.ordervo.OrderDetailVO2;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * <br/>
 * liyue 2018/6/29
 */
public interface UserOrderService extends BaseService<UserOrder> {
    /**
     * 根据qr_code找寻对象
     *
     * @param qr_code
     * @return
     */
    EnterprisePaymentVo getQrCode(String qr_code);

    /**
     * 查看商户所有订单
     *
     * @param id
     * @return
     */
    List<EnterpriseOrderVo> getEnterpriseAllOrder(@Param("id") Integer id, @Param("page") Page page);

    Integer getEnterpriseAllOrderCount(Integer id);

    /**
     * 获得商户今日订单
     *
     * @param id
     * @return
     */
    List<EnterpriseOrderVo> getEnterpriseNowOrder(@Param("id") Integer id, @Param("d") Timestamp d, @Param("t") Timestamp t, @Param("page") Page page);

    Integer getEnterpriseNowOrderCount(@Param("id") Integer id, @Param("d") Timestamp d, @Param("t") Timestamp t);


    /**
     * 获取商户未来订单
     *
     * @param id
     * @return
     */
    List<EnterpriseOrderVo> getEnterpriseFeatureOrder(@Param("id") Integer id, @Param("d") Timestamp d, @Param("page") Page page);

    Integer getEnterpriseFeatureOrderCount(@Param("id") Integer id, @Param("d") Timestamp d);

    /**
     * 获取商户取消订单
     *
     * @param id
     * @return
     */
    List<EnterpriseOrderVo> getEnterpriseDeleteOrder(@Param("id") Integer id, @Param("page") Page page);

    Integer getEnterpriseDeleteOrderCount(Integer id);

    /**
     * 获取商户已过期
     *
     * @param id
     * @return
     */
    List<EnterpriseOrderVo> getEnterpriseOverOrder(@Param("id") Integer id, @Param("page") Page page);

    Integer getEnterpriseOverOrderCount(Integer id);

    /**
     * 获取商户已消费
     *
     * @param id
     * @return
     */
    List<EnterpriseOrderVo> getEnterpriseConsumeOrder(@Param("id") Integer id, @Param("page") Page page);

    Integer getEnterpriseConsumeOrderCount(Integer id);

    /**
     * 获取企业订单
     *
     * @param name
     * @param id
     * @return
     */
    PageInfo<EnterpriseOrderVo> getEnterpriseOrder(String name, Integer id, Timestamp d, Timestamp t, Page page);

    /**
     * 获取商家7天、1个月、3个月、1年所成功订单
     *
     * @param id
     * @return
     */
    List<EnterpriseOrderVo> getEnterpriseMonthOrder(@Param("id") Integer id, @Param("d") Timestamp d, @Param("page") Page page);

    Integer getEnterpriseMonthOrderCount(Integer id);

    /**
     * @param id
     * @param d
     * @param page
     * @return
     */
    PageInfo<EnterpriseOrderVo> EnterpriseMonthOrder(Integer id, Timestamp d, Page page);

    /**
     * 获取活动下的订单
     *
     * @param aid
     * @param page
     * @return
     */
    PageInfo<UserOrder> getOrdersByAid(Integer aid, Page page);

    /**
     * 根据aid获取头像列表
     *
     * @param aid
     * @return
     */
    List<String> getOrdersPicture(@Param("aid") Integer aid);

    /**
     * 获取用户正在砍价的订单
     * @param uid 用户id
     */
    PageInfo<KanjiaOrderVo> listKanjiaOrders(Integer uid, Page page);

    /**
     * 列出我的订单
     * @param uid 用户id
     */
    PageInfo<MyOrderVo> listMyOrders(Integer uid, Page page);

    /**
     * 生成订单接口,注意直接购买类型的订单或者未指定类型的订单生成后的状态为待付款；拼团和砍价生成后的状态为正在拼团（砍价）
     */
    ReturnMessage generateOrder(Integer uid, Integer aid);

    /**
     * 获取订单详情
     * @param oid
     * @return
     */
    OrderDetailVO getOrderDetail(Integer oid);

    OrderDetailVO2 getOrderDetail2(Integer oid);

    /**
     * 根据订单状态，列出订单
     * @param uid
     * @param orderState
     * @param page
     * @return
     */
    PageInfo<OrderListVO> listOrders(Integer uid, Integer orderState, Page page);
    /**
     * 根据qr_code获取信息
     * @param order_number
     * @return
     */
    int[] getCurrentPrice(String order_number);
    /**
     * 根据二维码获取订单详情
     * @param qr_code
     * @return
     */
    OrderInfoVo getOrderInfo(String qr_code);

    /**
     * 删除订单接口
     */
    ReturnMessage deleteOrder(Integer oid);

    /**
     * 订单完成支付后调用
     */
    ReturnMessage finishPay(Integer oid);

    /**
     * 砍价活动订单提前结束砍价
     */
    ReturnMessage stopKanjia(Integer oid);
}