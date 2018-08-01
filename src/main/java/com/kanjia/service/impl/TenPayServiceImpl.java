package com.kanjia.service.impl;

import com.kanjia.service.TenPayService;
import com.kanjia.wxpay.ConstantUtil;
import com.kanjia.wxpay.MD5Util;
import com.kanjia.wxpay.PrepayIdRequestHandler;
import com.kanjia.wxpay.WXUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <br/>
 * fan 2018/6/5 16:56
 */
@Service
public class TenPayServiceImpl implements TenPayService {

    public Map<String, Object> getPrepayId(String openid, BigDecimal currentPrice, boolean isGenerator, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<String, Object>();
        PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);
        //拿到所传订单
        prepayReqHandler.setParameter("appid", ConstantUtil.APP_ID);
        prepayReqHandler.setParameter("body", ConstantUtil.BODY);
        prepayReqHandler.setParameter("mch_id", ConstantUtil.MCH_ID);
        String nonce_str = WXUtil.getNonceStr();
        prepayReqHandler.setParameter("nonce_str", nonce_str);
        prepayReqHandler.setParameter("notify_url", ConstantUtil.NOTIFY_URL);
        String timestamp = WXUtil.getTimeStamp();
        //对是否是创建者进行判断，返回不同的交易id
        String out_trade_no = null;
        int randomNumber = (int) (Math.random() * 100000);
        if(isGenerator){
            out_trade_no = "g" + timestamp + randomNumber;
        }else{
            out_trade_no = "e" + timestamp + randomNumber;
        }

        prepayReqHandler.setParameter("out_trade_no", out_trade_no);
        prepayReqHandler.setParameter("spbill_create_ip", request.getRemoteAddr());

        prepayReqHandler.setParameter("time_start", timestamp);
        prepayReqHandler.setParameter("openid", openid);


        long tmpPreferentialPrice = currentPrice.multiply(new BigDecimal(100)).longValue();
        prepayReqHandler.setParameter("total_fee", String.valueOf(tmpPreferentialPrice));
        prepayReqHandler.setParameter("trade_type", "JSAPI");
        /**
         * 注意签名（sign）的生成方式，具体见官方文档（传参都要参与生成签名，且参数名按照字典序排序，最后接上APP_KEY,转化成大写）
         */
        prepayReqHandler.setParameter("sign", prepayReqHandler.createMD5Sign());
        prepayReqHandler.setGateUrl(ConstantUtil.GATEURL);
        String prepayid = null;
        try {
            prepayid = prepayReqHandler.sendPrepay();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 若获取prepayid成功，将相关信息返回客户端
        if (prepayid != null && !prepayid.equals("")) {
            String signs = "appId=" + ConstantUtil.APP_ID + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepayid +"&signType=MD5"
                    + "&timeStamp=" + timestamp + "&key=" + ConstantUtil.APP_KEY;
            map.put("code", 0);
            map.put("info", "success");
            map.put("prepayid", prepayid);
            /**
             * 签名方式与上面类似
             */
            map.put("paySign", MD5Util.MD5Encode(signs, "utf8").toUpperCase());
//            map.put("appid", ConstantUtil.APP_ID);
            map.put("nonceStr", nonce_str);   //与请求prepayId时值一致
            map.put("package", "prepay_id=" + prepayid);
            map.put("signType", "MD5");
            map.put("timeStamp", timestamp);  //等于请求prepayId时的time_start
        } else {
            map.put("code", 1);
            map.put("info", "获取prepayid失败");
        }
        return map;
    }

}