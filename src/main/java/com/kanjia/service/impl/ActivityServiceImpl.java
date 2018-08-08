package com.kanjia.service.impl;

import com.kanjia.basic.Page;
import com.kanjia.basic.PageInfo;
import com.kanjia.mapper.*;
import com.kanjia.pojo.Activity;
import com.kanjia.pojo.ActivityDescription;
import com.kanjia.pojo.Enterprise;
import com.kanjia.service.ActivityService;
import com.kanjia.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl extends AbstractBaseServiceImpl<Activity> implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private UserOrderMapper userOrderMapper;
    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private ActivityDescriptionMapper activityDescriptionMapper;
    @Autowired
    private DescriptionPictureMapper descriptionPictureMapper;

    @Override
    public BaseMapper<Activity> getDao() {
        return activityMapper;
    }

//    @Override
//    public List<PageActivityVo> getAllActivity(Page page) {
//        return activityMapper.getAllActivity(page);
//    }

    @Override
    public PageInfo<PageActivityVo> getAllActivity(Page page) {
        PageInfo<PageActivityVo> pageInfo = new PageInfo<>();
        List<PageActivityVo> list = activityMapper.getAllActivity(page);
        int i = 0;
        for (PageActivityVo pageActivityVo : list) {

            List<String> list1=userOrderMapper.getOrdersPicture(pageActivityVo.getId());
            list.get(i).setHeadSculptureCount(list1.size());
            if(list1.size()>5) {
                list1 = list1.subList(0, 5);
            }
            list.get(i).setHeadSculpture(list1);
            ++i;
        }
        pageInfo.setTotal(activityMapper.getAllActivityCount());
        pageInfo.setRows(list);
        return pageInfo;
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
        return activityMapper.getNameActivity(name, page);
    }

    @Override
    public Integer getNameActivityCount(String name) {
        return activityMapper.getNameActivityCount(name);
    }
//   @Override
//   public  PageInfo<PageActivityVo> getActivity(String name, Page page){
//        PageInfo<PageActivityVo> pageInfo = new PageInfo<>();
//        pageInfo.setPageNum(page.getPageNumber());
//        pageInfo.setPageSize(page.getPageSize());
//       if("\u63a8\u8350".equals(name)){
//          // pageInfo.setRows(getAllActivity(page));
//           pageInfo.setTotal(getAllActivityCount());
//       }
//       else if("\u5176\u5b83".equals(name)){
//           pageInfo.setRows(getQitaActivity(page));
//           pageInfo.setTotal(getQitaActivityCount());
//       }
//       else if(null != name) {
//           pageInfo.setRows(getNameActivity(name,page));
//           pageInfo.setTotal(getNameActivityCount(name));
//       }
//       return  pageInfo;
//    }

    @Override
    public List<PageActivityVo> getEnterpriseAllActivity(Integer id, Page page) {
        return activityMapper.getEnterpriseAllActivity(id, page);
    }

    @Override
    public Integer getEnterpriseAllActivityCount(Integer id) {
        return activityMapper.getEnterpriseAllActivityCount(id);
    }

    @Override
    public List<PageActivityVo> getEnterpriseNowActivity(Integer id, Page page) {
        return activityMapper.getEnterpriseNowActivity(id, page);
    }

    @Override
    public Integer getEnterpriseNowActivityCount(Integer id) {
        return activityMapper.getEnterpriseNowActivityCount(id);
    }

    @Override
    public List<PageActivityVo> getEnterpriseDeleteActivity(Integer id, Page page) {
        return activityMapper.getEnterpriseDeleteActivity(id, page);
    }

    @Override
    public Integer getEnterpriseDeleteActivityCount(Integer id) {
        return activityMapper.getEnterpriseDeleteActivityCount(id);
    }

    @Override
    public List<PageActivityVo> getEnterpriseStockActivity(Integer id, Page page) {
        return activityMapper.getEnterpriseStockActivity(id,page);
    }

    @Override
    public Integer getEnterpriseStockActivityCount(Integer id) {
        return activityMapper.getEnterpriseStockActivityCount(id);
    }


    @Override
    public PageInfo<PageActivityVo> getEnterpriseActivity(String name, Integer id, Page page) {
        PageInfo<PageActivityVo> pageInfo = new PageInfo<>();

        pageInfo.setPageNum(page.getPageNumber());
        pageInfo.setPageSize(page.getPageSize());
        if ("全部".equals(name)) {
            int i=0;
            List<PageActivityVo> pageActivityVos =getEnterpriseAllActivity(id, page);
            for (PageActivityVo pageActivityVo : pageActivityVos) {

                List<String> list1=userOrderMapper.getOrdersPicture(pageActivityVo.getId());
                pageActivityVos.get(i).setHeadSculptureCount(list1.size());
                if(list1.size()>5) {
                    list1 = list1.subList(0, 5);
                }
                pageActivityVos.get(i).setHeadSculpture(list1);
                ++i;
            }
            pageInfo.setRows(pageActivityVos);
            pageInfo.setTotal(getEnterpriseAllActivityCount(id));
        } else if ("已下架".equals(name)) {
            int i=0;
            List<PageActivityVo> pageActivityVos =getEnterpriseDeleteActivity(id, page);
            for (PageActivityVo pageActivityVo : pageActivityVos) {

                List<String> list1=userOrderMapper.getOrdersPicture(pageActivityVo.getId());
                pageActivityVos.get(i).setHeadSculptureCount(list1.size());
                if(list1.size()>5) {
                    list1 = list1.subList(0, 5);
                }
                pageActivityVos.get(i).setHeadSculpture(list1);
                ++i;
            }
            pageInfo.setRows(pageActivityVos);
            pageInfo.setTotal(getEnterpriseDeleteActivityCount(id));
        } else if ("上架中".equals(name)) {
            int i=0;
            List<PageActivityVo> pageActivityVos =getEnterpriseNowActivity(id, page);
            for (PageActivityVo pageActivityVo : pageActivityVos) {

                List<String> list1=userOrderMapper.getOrdersPicture(pageActivityVo.getId());
                pageActivityVos.get(i).setHeadSculptureCount(list1.size());
                if(list1.size()>5) {
                    list1 = list1.subList(0, 5);
                }
                pageActivityVos.get(i).setHeadSculpture(list1);
                ++i;
            }
            pageInfo.setRows(pageActivityVos);
            pageInfo.setTotal(getEnterpriseNowActivityCount(id));
        }else if ("售罄".equals(name)) {
            int i=0;
            List<PageActivityVo> pageActivityVos =getEnterpriseStockActivity(id, page);
            for (PageActivityVo pageActivityVo : pageActivityVos) {

                List<String> list1=userOrderMapper.getOrdersPicture(pageActivityVo.getId());
                pageActivityVos.get(i).setHeadSculptureCount(list1.size());
                if(list1.size()>5) {
                    list1 = list1.subList(0, 5);
                }
                pageActivityVos.get(i).setHeadSculpture(list1);
                ++i;
            }
            pageInfo.setRows(pageActivityVos);
            pageInfo.setTotal(getEnterpriseStockActivityCount(id));
        }
        return pageInfo;
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
        EnterpriseVo enterpriseVo=enterpriseMapper.getEnterpriseId(id);
        list.get(0).setEnterprise(enterpriseVo);
        List<ActivityDescriptionVo> activityDescriptionVos=activityDescriptionMapper.getActivityId(id);
        for(int i=0;i<activityDescriptionVos.size();++i) {
            List<String> picture = descriptionPictureMapper.getDescriptionId(activityDescriptionVos.get(i).getId());

            activityDescriptionVos.get(i).setPicture(picture);
        }
        list.get(0).setActivityDescription(activityDescriptionVos);

        List<String> list1=userOrderMapper.getOrdersPicture(id);
        list.get(0).setHeadSculptureCount(list1.size());
        if(list1.size()>5) {
            list1 = list1.subList(0, 5);
        }
        list.get(0).setHeadSculpture(list1);

        return list.get(0);
    }


}
