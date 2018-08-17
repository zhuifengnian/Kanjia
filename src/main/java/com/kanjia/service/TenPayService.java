package com.kanjia.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 微信支付业务层<br/>
 * fan 2018/6/5 16:55
 */
public interface TenPayService {
    Map<String, Object> getPrepayId(String openid, BigDecimal currentPrice, boolean isGenerator, HttpServletRequest request, HttpServletResponse response);

    /**
     * 请求小程序码图片
     * @param page
     * @param scene
     * @param response
     */
    void requestMiniCodePicture(String page, String scene, HttpServletResponse response) throws IOException;
}