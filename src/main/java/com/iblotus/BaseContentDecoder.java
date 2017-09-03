package com.iblotus;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public abstract class BaseContentDecoder<T> implements ContentDecoder<T> {

    @Override
    public T decode(String rawString) {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(rawString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonNode errNode = jsonNode.findValue("error_response");
        if(errNode != null){
            String code = errNode.get("code").asText();
            String msg = errNode.get("msg").asText();
            String requestId = errNode.get("request_id").asText();
            throw  new TaobaoApiException(code, msg, requestId);
        }else {
            return this.realDecode(rawString);
        }
    }

    protected abstract T realDecode(String rawString);
}
