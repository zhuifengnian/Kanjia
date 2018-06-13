package com.express.service.impl;

import com.express.basic.Page;
import com.express.mapper.BaseMapper;
import com.express.service.BaseService;
import com.express.utils.ReflectUtil;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


@Transactional
public abstract class AbstractBaseServiceImpl<T> implements BaseService<T> {

    public abstract BaseMapper<T> getDao();

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return getDao().deleteByPrimaryKey(id);
    }
    public List<T> select(T record, Page page) {
        if (page == null)
            page = new Page();
        if ((page.getPageNumber() == null&&page.getStart()==null) || page.getPageSize() == null || page.getPageSize() >= 100) {
            page.setPageNumber(1);
            page.setPageSize(10);
        }
        return getDao().select(ReflectUtil.generalMap(record, page));
    }
    public int insertSelective(T record) {return getDao().insertSelective(record);}
    public int updateByPrimaryKey(T record) {
      return getDao().updateByPrimaryKey(record);
    }
    public int updateByPrimaryKeySelective(T record) {
        return getDao().updateByPrimaryKeySelective(record);
    }
    @Override
    public int selectCount(T record) {
        return getDao().selectCount(record);
    }

    @Override
    public int insert(T record) {
        getDao().insertSelective(record);
        try {
            if(record!=null)
                return  (Integer) ReflectUtil.getValueByName(record,"id");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public T selectByPrimaryKey(Integer id) {
        return getDao().selectByPrimaryKey(id);
    }

    public int update(T record) {
        return getDao().updateByPrimaryKeySelective(record);
    }



}
