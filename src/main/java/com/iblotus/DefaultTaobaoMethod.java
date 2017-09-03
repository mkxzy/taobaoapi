package com.iblotus;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

class DefaultTaobaoMethod implements TaobaoApiMethod {

    private String version = "2.0";
    private String signMethod = "hmac";
    private String format = "json";
    private String accessToken;
    private String methodName;
    private Map<String, ApiParam> params = new HashMap<String, ApiParam>();
    private TaobaoApiConfig config;
    private final String charset = "UTF-8";

    public DefaultTaobaoMethod(String methodName, TaobaoApiConfig config) {

        this.methodName = methodName;
        this.config = config;
    }

    private String getAccessToken() {

        return this.accessToken;
    }

    private String getName() {

        return this.methodName;
    }

    @Override
    public TaobaoApiMethod setParam(String name, ApiParam value) {
        params.put(name, value);
        return this;
    }

    @Override
    public TaobaoApiMethod setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    private Map<String, String> toApiParams() {

        Map<String, String> map = new HashMap<String, String>();
        for (Map.Entry<String, ApiParam> entry : this.params.entrySet()) {
            map.put(entry.getKey(), entry.getValue().format());
        }
        return map;
    }

    @Override
    public TaobaoApiResult call() throws IOException {

        String accessToken = this.getAccessToken();
        Map<String, String> paramss = this.toApiParams();
        paramss.put("v", version);
        paramss.put("app_key", config.getAppKey());
        if (accessToken != null && !accessToken.equals("")) {
            paramss.put("session", accessToken);
        }
        paramss.put("sign_method", signMethod);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        paramss.put("timestamp", formatter.format(new Date()));
        paramss.put("format", format);
        paramss.put("method", this.getName());
        try {
            paramss.put("sign", SignHelper.signTopRequest(paramss, config.getSecret()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost post = new HttpPost(config.getUrl());
        post.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for(Map.Entry<String, String> entry: paramss.entrySet()){
            pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        StringEntity stringEntity = new StringEntity(
                URLEncodedUtils.format(pairs, charset),
                charset);
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpclient.execute(post);
        String result = this.readString(response.getEntity().getContent());
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(result);
//        JsonNode errNode = jsonNode.findValue("error_response");
//        if(errNode != null){
//            String code = errNode.get("code").asText();
//            String msg = errNode.get("msg").asText();
//            String requestId = errNode.get("request_id").asText();
//            throw new TaobaoApiException(code, msg, requestId);
//        }
        System.out.println(result);
        return new TaobaoApiResult(result);
    }

    private String readString(InputStream inputStream) throws IOException {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString(charset);
    }
}
