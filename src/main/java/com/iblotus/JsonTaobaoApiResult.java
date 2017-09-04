package com.iblotus;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by xiezhiyan on 17-8-31.
 * 淘宝API调用结果
 */
public class JsonTaobaoApiResult implements TaobaoApiResult {

    private final String rawString;
    private ContentDecoder resultDecoder;

    public JsonTaobaoApiResult(String rawString, ContentDecoder resultDecoder){

        this.rawString = rawString;
        this.resultDecoder = resultDecoder;
    }

    public JsonTaobaoApiResult(String rawString){
        this(rawString, null);
    }

    @Override
    public String toString(){
        return this.rawString;
    }

    public <T> T getObject(ContentDecoder<T> decoder){

        TaobaoApiException exception = this.getException();
        if(exception != null){
            throw exception;
        }
        return decoder.decode(this.rawString);
    }

    @Override
    public Object getObject(){

        if(this.resultDecoder == null){
            throw new RuntimeException("未设置解码器");
        }
        return this.getObject(this.resultDecoder);
    }

    @Override
    public TaobaoApiException getException(){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(this.rawString);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        JsonNode errNode = jsonNode.findValue("error_response");
        if(errNode != null){
            String code = errNode.get("code").asText();
            String msg = errNode.get("msg").asText();
            String requestId = errNode.get("request_id").asText();
            return new TaobaoApiException(code, msg, requestId);
        }else{
            return null;
        }
    }
}
