package com.kanjia.utils;

import com.kanjia.basic.Page;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liyue
 * 封装page设置
 */
public class PageUtil {

    public static Page setPage(Integer pageNumber){
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(4);
        return page;
    }





}