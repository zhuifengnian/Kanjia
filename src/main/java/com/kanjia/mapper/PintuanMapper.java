package com.kanjia.mapper;

import com.kanjia.dto.PintuanInfoDto;
import com.kanjia.pojo.Pintuan;
import com.kanjia.vo.OrderUserVO;

import java.util.List;

public interface PintuanMapper extends BaseMapper<Pintuan>  {
    /**
     * 根据订单号获得团号
     */
    Integer getPintuanId(Integer oid);

    /**
     * 获取团中订单的数量
     * @param tuanid
     */
    Integer getOrderCount(Integer tuanid);

    /**
     * 获取拼团信息
     */
    PintuanInfoDto getPintuanInfo(Integer tuanid);

    /**
     * 获取拼团用户
     */
    List<OrderUserVO> getPintuanUser(Integer tuanid);
}