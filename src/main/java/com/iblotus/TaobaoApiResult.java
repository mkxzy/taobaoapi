package com.iblotus;

public interface TaobaoApiResult {

    <T> T getObject(ContentDecoder<T> decoder);

    Object getObject();

    TaobaoApiException getException();
}
