package com.iblotus;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by xiezhiyan on 17-9-3.
 */
public class TaobaoApiMethodTest {

    @Test
    public void testCall() throws IOException {
        TaobaoApiConfig config = new TaobaoApiConfig("https://gw.api.tbsandbox.com/router/rest",
                "1021035674",
                "sandbox68cb8f35e1d06f8154c1551de");
        TaobaoApiMethod method = config.createMethod("taobao.shop.get")
                .setParam("fields", ApiParams.arrayParam("sid", "cid"))
                .setParam("nick", ApiParams.stringParam("sandbox_cilai_c"));
        TaobaoApiResult result = method.call();
        Assert.assertEquals(result.getClass(), JsonTaobaoApiResult.class);
//        System.out.println(result.getException());
    }

    @Test
    public void testCallWithConfig() throws IOException {
        ContentDecoder decoder = mock(ContentDecoder.class);
        Object returnObject = new HashMap();
        when(decoder.decode(anyString())).thenReturn(returnObject);
        TaobaoApiConfig config = new TaobaoApiConfig("https://gw.api.tbsandbox.com/router/rest",
                "1021035674",
                "sandbox68cb8f35e1d06f8154c1551de");
        config.addDecoder("taobao.shop.get", decoder);
        TaobaoApiMethod method = config.createMethod("taobao.shop.get")
                .setParam("fields", ApiParams.arrayParam("sid", "cid"))
                .setParam("nick", ApiParams.stringParam("sandbox_cilai_c"));
        TaobaoApiResult result = method.call();
        Assert.assertEquals(result.getClass(), JsonTaobaoApiResult.class);
        System.out.println(result.getObject());
    }
}
