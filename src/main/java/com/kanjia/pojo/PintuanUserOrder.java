package com.kanjia.pojo;

import com.kanjia.basic.Const;
import com.kanjia.basic.Observer;
import com.kanjia.mapper.PintuanUserOrderMapper;
import com.kanjia.mapper.UserOrderMapper;

import java.util.Date;
import java.util.Observable;

public class PintuanUserOrder implements Observer {
    private Integer id;

    private Integer pintuanId;

    private Integer orderId;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    public PintuanUserOrder(Integer id, Integer pintuanId, Integer orderId, Integer state, Date createTime, Date updateTime) {
        this.id = id;
        this.pintuanId = pintuanId;
        this.orderId = orderId;
        this.state = state;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public PintuanUserOrder() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPintuanId() {
        return pintuanId;
    }

    public void setPintuanId(Integer pintuanId) {
        this.pintuanId = pintuanId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public void update(Object obj, Object obj2) {
        UserOrder userOrder = new UserOrder();
        userOrder.setId((Integer) obj2);
        userOrder.setState(Const.ORDER_STATUS_WAITING_CONCUMUE);
        ((UserOrderMapper)obj).updateByPrimaryKeySelective(userOrder);
    }
}