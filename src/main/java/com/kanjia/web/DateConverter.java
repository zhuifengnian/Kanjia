package com.kanjia.web;

import com.kanjia.utils.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * 将从前台传入的字符串类型的数据转换成Date类型
 */
public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        return DateUtils.parseDate(s);
    }
}