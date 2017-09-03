package com.iblotus;

/**
 * 淘宝接口代理类
 */
public class TaobaoApiConfig {

    private String url;

    private String appKey;

    private String secret;

    public TaobaoApiConfig(String url, String appKey, String secret){

        this.url = url;
        this.appKey = appKey;
        this.secret = secret;
    }

    public String getUrl() {
        return url;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getSecret() {
        return secret;
    }

    public TaobaoApiMethod createMethod(String methodName) {

        return new DefaultTaobaoMethod(methodName, this);
    }
}
