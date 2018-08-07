package com.kanjia.mapper;

import com.kanjia.basic.Page;
import com.kanjia.pojo.EnterpriseBill;
import com.kanjia.vo.EnterpriseBillVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EnterpriseBillMapper extends BaseMapper<EnterpriseBill> {
    /**
     * 根据eid获取列表
     * @param eid
     * @return
     */
    List<EnterpriseBillVo> getListBill(@Param("eid") Integer eid, @Param("page") Page page);

    /**
     * 获取当月钱数
     * @return
     */
    List<EnterpriseBill> getMoney(Integer eid);
}