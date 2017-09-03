package com.iblotus;

import java.util.Map;

/**
 * Created by xiezhiyan on 17-8-31.
 * 淘宝API调用结果
 */
public class TaobaoApiResult {

    private final String rawString;
    private Map<String, ContentDecoder> decoderMap;

    public TaobaoApiResult(String rawString, Map<String, ContentDecoder> decoderMap){
        this.rawString = rawString;
        this.decoderMap = decoderMap;
    }

    public TaobaoApiResult(String rawString){
        this(rawString, null);
    }

    @Override
    public String toString(){
        return this.rawString;
    }

    public <T> T getObject(ContentDecoder<T> decoder){

        return decoder.decode(this.rawString);
    }

    public Object getObject(String name){
        if(decoderMap == null)
            throw new RuntimeException("请设置解码器");
        return decoderMap.get(name).decode(this.rawString);
    }
}
