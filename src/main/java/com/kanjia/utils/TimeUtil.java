package com.kanjia.utils;


import java.sql.Timestamp;
import java.util.Random;
import java.util.TimeZone;

/**
 * @Author liyue
 * 封装时间
 */
public class TimeUtil {

    private static final int MIN = 100000000;
    private static final int MAX = 900000000;


    public static Timestamp[] getTime() {
        long current = System.currentTimeMillis();
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve = zero + 24 * 60 * 60 * 1000 - 1;

        Timestamp t1 = new Timestamp(zero);
        Timestamp t2 = new Timestamp(twelve);
        Timestamp t[] = {t1, t2};
        return t;
    }

    /**
     * 生成9位的随机数
     */
    public static String random9Number(){
        Random random = new Random();
        return MIN + random.nextInt(MAX) + "";
    }

    /**
     * 生成时间戳
     */
    public static long currentTimeStamp(){
        return System.currentTimeMillis();
    }

}