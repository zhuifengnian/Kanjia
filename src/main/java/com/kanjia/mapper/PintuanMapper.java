package com.kanjia.mapper;

import com.kanjia.pojo.Pintuan;

public interface PintuanMapper extends BaseMapper<Pintuan>  {
    /**
     * 根据订单号获得团号
     */
    Integer getPintuanId(Integer oid);
}