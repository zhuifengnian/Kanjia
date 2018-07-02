package com.kanjia.mapper;

import com.kanjia.basic.Page;
import com.kanjia.pojo.Activity;
import com.kanjia.vo.DetailActivityVo;
import com.kanjia.vo.PageActivityVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityMapper extends BaseMapper<Activity> {
    /**
     * 获取活动列表
     *
     * @param page
     * @return
     */
    List<PageActivityVo> getAllActivity(Page page);

    Integer getAllActivityCount();


    /**
     * 获取其他活动
     *
     * @param page
     * @return
     */
    List<PageActivityVo> getQitaActivity(Page page);

    Integer getQitaActivityCount();

    /**
     * 通过传入name获取类别活动
     *
     * @param name
     * @param page
     * @return
     */
    List<PageActivityVo> getNameActivity(@Param("name") String name, @Param("page") Page page);

    Integer getNameActivityCount(String name);

    /**
     * 查看商户所有活动
     *
     * @param id
     * @return
     */
    List<PageActivityVo> getEnterpriseAllActivity(@Param("id") Integer id, @Param("page") Page page);

    Integer getEnterpriseAllActivityCount(Integer id);

    /**
     * 获得商户正上线活动
     *
     * @param id
     * @return
     */
    List<PageActivityVo> getEnterpriseNowActivity(@Param("id") Integer id, @Param("page") Page page);

    Integer getEnterpriseNowActivityCount(Integer id);


    /**
     * 获取商户已删除活动
     *
     * @param id
     * @return
     */
    List<PageActivityVo> getEnterpriseDeleteActivity(@Param("id") Integer id, @Param("page") Page page);

    Integer getEnterpriseDeleteActivityCount(Integer id);

    /**
     * 获取活动详情
     *
     * @param id
     * @return
     */
    List<DetailActivityVo> getDetailActivity(Integer id);

    Integer getDetailActivityCount(Integer id);


}