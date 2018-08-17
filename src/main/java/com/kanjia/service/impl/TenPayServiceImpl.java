package com.kanjia.service.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kanjia.service.TenPayService;
import com.kanjia.wxpay.ConstantUtil;
import com.kanjia.wxpay.MD5Util;
import com.kanjia.wxpay.PrepayIdRequestHandler;
import com.kanjia.wxpay.WXUtil;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
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

    @Override
    public void requestMiniCodePicture(String page, String scene, HttpServletResponse response) throws IOException {
        URL url = new URL("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + ConstantUtil.APP_ID2 +
                "&secret=" + ConstantUtil.APP_SECRET2);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setConnectTimeout(2000);
        urlConnection.setDoInput(true);
        int responseCode = urlConnection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){
            BufferedInputStream bis = new BufferedInputStream(urlConnection.getInputStream());
            byte[] buf = new byte[1024];
            int len;
            StringBuffer sb = new StringBuffer();
            while((len = bis.read(buf)) >= 0){
                sb.append(new String(buf, 0 , len));
            }
            String ret = sb.toString();
            JsonParser parser = new JsonParser();
            JsonElement parse1 = parser.parse(ret);
            String access_token = parse1.getAsJsonObject().get("access_token").getAsString();

            getQrCodePicture(access_token, page, scene, response);
        }
//        return null;
    }

    /**
     * 从微信后台获得数据流，并将其生成图片文件
     *
     * @param access_token  微信给的凭证
     * @param page  生成的页面路径
     * @param scene 传入的参数
     * @param response
     * @throws IOException
     */
    private void getQrCodePicture(String access_token, String page, String scene, HttpServletResponse response) throws IOException {
        URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + access_token);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");// 提交模式
        // conn.setConnectTimeout(10000);//连接超时 单位毫秒
        // conn.setReadTimeout(2000);//读取超时 单位毫秒
        // 发送POST请求必须设置如下两行
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        // 获取URLConnection对象对应的输出流
        PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
        // 发送请求参数
        JsonObject paramJson = new JsonObject();
        paramJson.addProperty("scene", scene);
        paramJson.addProperty("page", page);
        paramJson.addProperty("width", 430);
        paramJson.addProperty("auto_color", true);
        /**
         * line_color生效
         * paramJson.put("auto_color", false);
         * JSONObject lineColor = new JSONObject();
         * lineColor.put("r", 0);
         * lineColor.put("g", 0);
         * lineColor.put("b", 0);
         * paramJson.put("line_color", lineColor);
         * */

        printWriter.write(paramJson.toString());
        // flush输出流的缓冲
        printWriter.flush();
        //开始获取数据
        BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());

        //转码，免得文件名中文乱码
//        filename = URLEncoder.encode(filename,"UTF-8");
        String filename = "qrcode.png";
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("image/png");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        ServletOutputStream outputStream = response.getOutputStream();
        int len;
        byte[] arr = new byte[1024];
        while ((len = bis.read(arr)) != -1) {
            outputStream.write(arr, 0, len);
            outputStream.flush();
        }
        bis.close();
    }

}