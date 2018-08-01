package com.kanjia.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 微信支付业务层<br/>
 * fan 2018/6/5 16:55
 */
public interface TenPayService {
    Map<String, Object> getPrepayId(String openid, BigDecimal currentPrice, boolean isGenerator, HttpServletRequest request, HttpServletResponse response);
}