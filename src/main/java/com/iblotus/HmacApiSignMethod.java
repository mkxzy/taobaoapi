package com.iblotus;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by xiezhiyan on 17-9-3.
 * Hmac算法
 */
class HmacApiSignMethod implements ApiSignMethod {

    private final String secret;
    private static final String CHARSET = "UTF-8";

    public HmacApiSignMethod(final String secret){
        this.secret = secret;
    }

    @Override
    public String sign(Map<String, String> params){
        // 第一步：检查参数是否已经排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 第二步：把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();
        for (String key : keys) {
            String value = params.get(key);
            if (areNotEmpty(key, value)) {
                query.append(key).append(value);
            }
        }

        // 第三步：使用MD5/HMAC加密
        byte[] bytes = encryptHMAC(query.toString());

        // 第四步：把二进制转化为大写的十六进制
        return byte2hex(bytes);
    }

    private byte[] encryptHMAC(String data){
        byte[] bytes = null;
        try {
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(CHARSET), "HmacMD5");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes(CHARSET));
        } catch (GeneralSecurityException gse) {
            throw new RuntimeException(gse);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }

    private static boolean areNotEmpty(String key, String value){
        if(key == null || key.equals("")){
            return false;
        }
        if(value == null || value.equals("")){
            return false;
        }
        return true;
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }
}
