package com.example.tarena.groupon.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by tarena on 2017/6/19.
 */

public class HttpUtil {
    private static final String APPKEY = "49814079";
    private static final String APPSECRECT = "90e3438a41d646848033b6b9d461ed54";

    //获得满足大众点评要求的请求路径
    public static String getURL(String url, Map<String, String> params) {
        String result = "";
        String sign = getSign(APPKEY, APPSECRECT, params);
        String query = getQuery(APPKEY, sign, params);
        return result;
    }

    private static String getSign(String appkey, String appsecrect, Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();

// 对参数名进行字典排序
        String[] keyArray = params.keySet().toArray(new String[0]);
        Arrays.sort(keyArray);
// 拼接有序的参数名-值串
        stringBuilder.append(appkey);
        for (String key : keyArray) {
            stringBuilder.append(key).append(params.get(key));
        }
        String codes = stringBuilder.append(appsecrect).toString();
        //在纯java环境中，
//        String sign = org.apache.commons.codec.digest.DigestUtils.shaHex(codes).toUpperCase();
//在安卓中
        String sign=new String(Hex.decodeHex(DigestUtils.sha(codes))).toUpperCase();
        return sign;
    }

    private static String getQuery(String appkey, String sign, Map<String, String> params) {

        try {
            // 添加签名
            StringBuilder  stringBuilder = new StringBuilder();
            stringBuilder.append("appkey=").append(appkey).append("&sign=").append(sign);
            for (Map.Entry<String, String> entry : params.entrySet())
            {
                stringBuilder.append('&').append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue(),"utf-8"));
            }
            String queryString = stringBuilder.toString();
            return queryString;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            //仍异常
            throw new RuntimeException("使用了不正确的字符");

        }
    }
}
