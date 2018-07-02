package com.kanjia.service;

import com.kanjia.basic.Page;
import com.kanjia.basic.PageInfo;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.pojo.Activity;
import com.kanjia.pojo.User;
import com.kanjia.vo.DetailActivityVo;
import com.kanjia.vo.EnterpriseOrderVo;
import com.kanjia.vo.PageActivityVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * liyue 2018.6.29
 */
public interface ActivityService extends BaseService<Activity>{
    /**
     * 获取活动列表
     * @param page
     * @return
     */
    List<PageActivityVo> getAllActivity(Page page);
    Integer getAllActivityCount();



    /**
     * 获取其他活动
     * @param page
     * @return
     */
    List<PageActivityVo>  getQitaActivity(Page page);

    Integer getQitaActivityCount();

    /**
     * 通过传入name获取类别活动
     * @param name
     * @param page
     * @return
     */
    List<PageActivityVo> getNameActivity(@Param("name") String name, @Param("page")  Page page);
    Integer getNameActivityCount(String name);

    /**
     *
     * @param name
     * @param page
     * @return
     */
  PageInfo<PageActivityVo> getActivity(String name, Page page);

    /**
     * 查看商户所有活动
     * @param id
     * @return
     */
    List<PageActivityVo> getEnterpriseAllActivity(@Param("id") Integer id,@Param("page") Page page);
    Integer getEnterpriseAllActivityCount(Integer id);
    /**
     * 获得商户正上线活动
     * @param id
     * @return
     */
    List<PageActivityVo> getEnterpriseNowActivity( @Param("id") Integer id,@Param("page") Page page);
    Integer getEnterpriseNowActivityCount(Integer id);



    /**
     * 获取商户已删除活动
     * @param id
     * @return
     */
    List<PageActivityVo>  getEnterpriseDeleteActivity(@Param("id") Integer id,@Param("page") Page page);

    Integer getEnterpriseDeleteActivityCount(Integer id);

    /**
     *获取企业活动
     * @param name
     * @param id
     * @param page
     * @return
     */
    PageInfo<PageActivityVo> getEnterpriseActivity(String name,Integer id,Page page);
    /**
     * 获取活动详情
     * @param id
     * @return
     */
    List<DetailActivityVo> getDetailActivity(Integer id);
    Integer getDetailActivityCount(Integer id);
    /**
     *获取活动详情
     * @param
     * @return
     */
   DetailActivityVo getDetailsActivity(Integer id);

    /**
     * 根据活动id获得此活动下的已经生成订单的用户头像列表
     */
    List<String> getOrderUserAvatarByAid(Integer aid, Page page);

}