package com.kanjia.wxpay;


public class ConstantUtil {
    /**
     * 获取微信小程序openid的接口地址
     */
    public static final String GET_OPENID_URL = "https://api.weixin.qq.com/sns/jscode2session";
    /**
     * 微信开发平台应用ID
     */
    public static final String APP_ID = "wxfa5900e974154497";
    /**
     * 应用对应的凭证
     */
    public static final String APP_SECRET = "e4cb47c0a8aa5d9fa1c644e13e8ebeee";
    /**
     * 商户端小程序appid
     */
    public static final String APP_ID2="wx9ccf4cb41902b28c";
    /**
     * 商户端小程序凭证
     */
    public static final String APP_SECRET2 = "9a4f1bc28ffffc42837a5ba3ce6b9f2b";
    /**
     * 应用对应的密钥
     */
    public static final String APP_KEY = "sinianlife13163248472sinianlife8";
    /**
     * 微信支付商户号
     */
    public static final String MCH_ID = "1253466501";
    /**
     * 商品描述
     */
    public static final String BODY = "pick-测试支付";
    /**
     * 商户号对应的密钥
     */
    public static final String PARTNER_key = "123466";

    /**
     * 商户id
     */
//    public static final String PARTNER_ID="14698sdfs402dsfdew402";
    public static final String PARTNER_ID = "1253466501";
    /**
     * 常量固定值
     */
    public static final String GRANT_TYPE = "client_credential";
    /**
     * 获取预支付id的接口url
     */
    public static String GATEURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * 微信服务器回调通知url
     */
//    public static String NOTIFY_URL="http://www.bairuoheng.cn/app/tenpay/notify";
    public static String NOTIFY_URL = "http://localhost:8080/app/tenpay/notify";
}