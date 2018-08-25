package com.kanjia.service;

import com.kanjia.basic.ReturnMessage;
import com.kanjia.pojo.Pintuan;

/**
 * <br/>
 * fan 2018/8/21 0:47
 */
public interface PintuanService extends BaseService<Pintuan>{
    /**
     * 用户参团接口
     */
    ReturnMessage insertTuan(Integer tuanid, Integer uid);

    /**
     * 根据订单号查询所在的团号
     */
    ReturnMessage getPintuanId(Integer oid);

    /**
     * 获取拼团信息
     */
    ReturnMessage getPintuanInfo(Integer tuanid);

    /**
     * 参团支付接口
     * @param tuanid
     * @param oid
     * @return
     */
    ReturnMessage insertTuanFinishPay(Integer tuanid, Integer oid);
}