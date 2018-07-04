package com.kanjia.service.impl;

import com.kanjia.basic.Page;
import com.kanjia.basic.PageInfo;
import com.kanjia.mapper.BaseMapper;
import com.kanjia.mapper.UserOrderMapper;
import com.kanjia.pojo.UserOrder;
import com.kanjia.service.UserOrderService;
import com.kanjia.vo.EnterpriseOrderVo;
import com.kanjia.vo.EnterprisePaymentVo;
import com.kanjia.vo.KanjiaOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserOrderServiceImpl extends AbstractBaseServiceImpl<UserOrder> implements UserOrderService {
    @Autowired
    private UserOrderMapper userOrderMapper;

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
        if ("今日订单".equals(name)) {
            pageInfo.setRows(getEnterpriseNowOrder(id, d, t, page));
            pageInfo.setTotal(getEnterpriseNowOrderCount(id, d, t));
        } else if ("已取消".equals(name)) {
            pageInfo.setRows(getEnterpriseDeleteOrder(id, page));
            pageInfo.setTotal(getEnterpriseDeleteOrderCount(id));
        } else if ("已过期".equals(name)) {
            pageInfo.setRows(getEnterpriseOverOrder(id, page));
            pageInfo.setTotal(getEnterpriseOverOrderCount(id));
        } else if ("全部".equals(name)) {
            pageInfo.setRows(getEnterpriseAllOrder(id, page));
            pageInfo.setTotal(getEnterpriseAllOrderCount(id));

        } else if ("未来".equals(name)) {
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
        kanjiaOrderVoPageInfo.setRows(kanjiaOrderVos);
        kanjiaOrderVoPageInfo.setTotal(userOrderMapper.listKanjiaOrdersCount(uid));
        if(page != null){
            kanjiaOrderVoPageInfo.setPageNum(page.getPageNumber());
            kanjiaOrderVoPageInfo.setPageSize(page.getPageSize());
        }
        return  kanjiaOrderVoPageInfo;
    }


}
