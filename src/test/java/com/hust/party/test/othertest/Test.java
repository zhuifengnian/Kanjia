package com.hust.party.test.othertest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kanjia.wxpay.ConstantUtil;

import javax.json.Json;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Test {
    public static void main(String[] args) {
        try {
            requestAccessTocken();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从微信后台获得数据流，并将其生成图片文件
     * @param access_token
     * @throws IOException
     */
    private static void getQrCode(String access_token) throws IOException {
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
        paramJson.addProperty("scene", "a=1234567890");
        paramJson.addProperty("page", "pages/index/index");
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
        OutputStream os = new FileOutputStream(new File("d://abce.png"));
        int len;
        byte[] arr = new byte[1024];
        while ((len = bis.read(arr)) != -1) {
            os.write(arr, 0, len);
            os.flush();
        }
        os.close();
    }

    /**
     * 请求访问tocken
     * @throws IOException
     */
    private static void requestAccessTocken() throws IOException {
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

            getQrCode(access_token);
        }

    }
}