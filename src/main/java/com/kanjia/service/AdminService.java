package com.kanjia.service;

import com.kanjia.basic.Page;
import com.kanjia.basic.PageInfo;
import com.kanjia.pojo.Activity;
import com.kanjia.pojo.Admin;
import com.kanjia.pojo.Enterprise;
import com.kanjia.vo.DetailActivityVo;
import com.kanjia.vo.PageActivityVo;
import com.kanjia.vo.adminvo.AdminVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * liyue 2018.6.29
 */
public interface AdminService extends BaseService<Admin> {

 /**
  * 通过open_id获得eid
  *
  * @param openId
  * @return
  */
 Integer getId(String openId);
 /**
  * 获取企业所有信息
  * @param page
  * @return
  */
 PageInfo<AdminVo> getListEnterprise(Page page);
 /**
  * 获取企业所有信息数量
  * @return
  */
 Integer getListEnterpriseCount();

}