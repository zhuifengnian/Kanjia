package com.kanjia.mapper;

import com.kanjia.basic.Page;
import com.kanjia.pojo.UserOrder;
import com.kanjia.vo.EnterpriseOrderVo;
import com.kanjia.vo.EnterprisePaymentVo;
import com.kanjia.vo.KanjiaOrderVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface UserOrderMapper extends BaseMapper<UserOrder> {
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
     * 获取商家7天、1个月、3个月、1年所成功订单
     *
     * @param id
     * @return
     */
    List<EnterpriseOrderVo> getEnterpriseMonthOrder(@Param("id") Integer id, @Param("d") Timestamp d, @Param("page") Page page);

    Integer getEnterpriseMonthOrderCount(Integer id);

    List<UserOrder> getOrdersByAid(@Param("aid") Integer aid, @Param("page") Page page);

    /**
     * 获取活动下的订单总数
     *
     * @param aid
     * @return
     */
    Integer getOrdersByAidCount(Integer aid);

    /**
     * 根据aid获取头像列表
     *
     * @param aid
     * @return
     */
    List<String> getOrdersPicture(@Param("aid") Integer aid);

    /**
     * 列出正在砍价的订单
     * @param uid 用户id
     */
    List<KanjiaOrderVo> listKanjiaOrders(@Param("uid") Integer uid,@Param("page") Page page);

    Integer listKanjiaOrdersCount(Integer uid);
}