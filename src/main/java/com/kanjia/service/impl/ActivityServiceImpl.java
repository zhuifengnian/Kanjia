package com.kanjia.service.impl;

import com.kanjia.basic.Page;
import com.kanjia.basic.PageInfo;
import com.kanjia.basic.ResponseCode;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.mapper.ActivityMapper;
import com.kanjia.mapper.BaseMapper;
import com.kanjia.mapper.PictureMapper;
import com.kanjia.pojo.Activity;
import com.kanjia.service.ActivityService;
import com.kanjia.utils.QiNiuUtil;
import com.kanjia.vo.DetailActivityVo;
import com.kanjia.vo.EnterpriseOrderVo;
import com.kanjia.vo.PageActivityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ActivityServiceImpl extends AbstractBaseServiceImpl<Activity> implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private PictureMapper pictureMapper;
    @Override
    public BaseMapper<Activity> getDao() {
        return activityMapper;
    }

    @Override
    public List<PageActivityVo> getAllActivity(Page page) {
        return activityMapper.getAllActivity(page);
    }

    @Override
    public Integer getAllActivityCount() {
        return activityMapper.getAllActivityCount();
    }

    @Override
    public List<PageActivityVo> getQitaActivity(Page page) {
        return activityMapper.getQitaActivity(page);
    }

    @Override
    public Integer getQitaActivityCount() {
        return activityMapper.getQitaActivityCount();
    }

    @Override
    public List<PageActivityVo> getNameActivity(String name, Page page) {
        return activityMapper.getNameActivity(name,page);
    }

    @Override
    public Integer getNameActivityCount(String name) {
        return activityMapper.getNameActivityCount(name);
    }
   @Override
   public  PageInfo<PageActivityVo> getActivity(String name, Page page){
        PageInfo<PageActivityVo> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(page.getPageNumber());
        pageInfo.setPageSize(page.getPageSize());
       if("\u63a8\u8350".equals(name)){
           pageInfo.setRows(getAllActivity(page));
           pageInfo.setTotal(getAllActivityCount());
       }
       else if("\u5176\u5b83".equals(name)){
           pageInfo.setRows(getQitaActivity(page));
           pageInfo.setTotal(getQitaActivityCount());
       }
       else if(null != name) {
           pageInfo.setRows(getNameActivity(name,page));
           pageInfo.setTotal(getNameActivityCount(name));
       }
       return  pageInfo;
    }

    @Override
    public List<PageActivityVo> getEnterpriseAllActivity(Integer id, Page page) {
        return activityMapper.getEnterpriseAllActivity(id,page);
    }

    @Override
    public Integer getEnterpriseAllActivityCount(Integer id) {
        return activityMapper.getEnterpriseAllActivityCount(id);
    }

    @Override
    public List<PageActivityVo> getEnterpriseNowActivity(Integer id, Page page) {
        return activityMapper.getEnterpriseNowActivity(id,page);
    }

    @Override
    public Integer getEnterpriseNowActivityCount(Integer id) {
        return activityMapper.getEnterpriseNowActivityCount(id);
    }

    @Override
    public List<PageActivityVo> getEnterpriseDeleteActivity(Integer id, Page page) {
        return activityMapper.getEnterpriseDeleteActivity(id,page);
    }

    @Override
    public Integer getEnterpriseDeleteActivityCount(Integer id) {
        return activityMapper.getEnterpriseDeleteActivityCount(id);
    }


    @Override
    public  PageInfo<PageActivityVo> getEnterpriseActivity(String name,Integer id,Page page){
        PageInfo<PageActivityVo> pageInfo = new PageInfo<>();

        pageInfo.setPageNum(page.getPageNumber());
       pageInfo.setPageSize(page.getPageSize());
        if("全部".equals(name)){
            pageInfo.setRows(getEnterpriseAllActivity(id,page));
            pageInfo.setTotal(getEnterpriseAllActivityCount(id));
        }
        else if("删除".equals(name)){
            pageInfo.setRows(getEnterpriseDeleteActivity(id,page));
            pageInfo.setTotal(getEnterpriseDeleteActivityCount(id));
        }
        else if("上线".equals(name)) {
            pageInfo.setRows(getEnterpriseNowActivity(id,page));
            pageInfo.setTotal(getEnterpriseNowActivityCount(id));
        }
        return  pageInfo;
    }

    @Override
    public List<DetailActivityVo> getDetailActivity(Integer id) {
        return activityMapper.getDetailActivity(id);
    }

    @Override
    public Integer getDetailActivityCount(Integer id) {
        return activityMapper.getDetailActivityCount(id);
    }

    @Override
    public DetailActivityVo getDetailsActivity(Integer id) {

       List<DetailActivityVo> list = activityMapper.getDetailActivity(id);
        list.get(0).setPicture(pictureMapper.getPicture(list.get(0).getId()));


        return list.get(0);
    }


}
