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
//    void engageGroup(Integer gid, Integer )

    /**
     * 根据订单号查询所在的团号
     */
    ReturnMessage getPintuanId(Integer oid);

    /**
     * 将拼团订单插入到团中
     */
    ReturnMessage insertOrderToPintuan(Integer tuanid, Integer oid);

    /**
     * 获取拼团信息
     */
    ReturnMessage getPintuanInfo(Integer tuanid);
}