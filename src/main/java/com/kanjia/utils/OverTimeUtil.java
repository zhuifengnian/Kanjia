package com.kanjia.utils;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author liyue
 * 封装时间
 */
public class OverTimeUtil {

    public static Timestamp getTime(String name) {
        Calendar c = Calendar.getInstance();
        Date d = new Date();
        Timestamp ts = new Timestamp(d.getTime());
        if ("七天".equals(name))
        //过去七天
        {
            c.setTime(new Date());
            c.add(Calendar.DATE, -7);
            d = c.getTime();
            ts = new Timestamp(d.getTime());
        } else if ("一个月".equals(name)) {
            //过去一月
            c.setTime(new Date());
            c.add(Calendar.MONTH, -1);
            d = c.getTime();
            ts = new Timestamp(d.getTime());
        } else if ("三个月".equals(name)) {
            //过去三个月
            c.setTime(new Date());
            c.add(Calendar.MONTH, -3);
            d = c.getTime();
            ts = new Timestamp(d.getTime());
        } else if ("一年".equals(name)) {
            //过去一年
            c.setTime(new Date());
            c.add(Calendar.YEAR, -1);
            d = c.getTime();
            ts = new Timestamp(d.getTime());
        }

        return ts;
    }

}