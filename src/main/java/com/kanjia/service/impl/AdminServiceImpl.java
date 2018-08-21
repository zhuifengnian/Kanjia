package com.kanjia.service.impl;

import com.kanjia.basic.Page;
import com.kanjia.basic.PageInfo;
import com.kanjia.mapper.AdminMapper;
import com.kanjia.mapper.BaseMapper;
import com.kanjia.pojo.Admin;
import com.kanjia.pojo.Enterprise;
import com.kanjia.service.AdminService;
import com.kanjia.vo.adminvo.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends AbstractBaseServiceImpl<Admin> implements AdminService {
   @Autowired
   private AdminMapper adminMapper;

    @Override
    public BaseMapper<Admin> getDao() {
        return adminMapper;
    }

    @Override
    public Integer getId(String openId) {
        return adminMapper.getId(openId);
    }

    @Override
    public PageInfo<AdminVo> getListEnterprise(Page page) {
        PageInfo<AdminVo> adminVoPageInfo=new PageInfo<>();
        if(page!=null) {
            adminVoPageInfo.setPageNum(page.getPageNumber());
            adminVoPageInfo.setPageSize(page.getPageSize());
        }
        adminVoPageInfo.setTotal(adminMapper.getListEnterpriseCount());
        adminVoPageInfo.setRows(adminMapper.getListEnterprise(page));
        return adminVoPageInfo;
    }

    @Override
    public Integer getListEnterpriseCount() {
        return adminMapper.getListEnterpriseCount();
    }


}
