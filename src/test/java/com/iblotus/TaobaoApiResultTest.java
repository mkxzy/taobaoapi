package com.iblotus;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TaobaoApiResultTest {

    @Test
    public void testGetRawString(){

        String rawString = "{\n" +
                "    \"id\": null,\n" +
                "    \"title\": \"sandbox_cilai_c的沙箱测试店铺\",\n" +
                "    \"nick\": null,\n" +
                "    \"accessToken\": null,\n" +
                "    \"products\": []\n" +
                "}";
        JsonTaobaoApiResult result = new JsonTaobaoApiResult(rawString);
        Assert.assertEquals(rawString, result.toString());
    }

    @Test
    public void testGetObject(){
        String rawString = "{\n" +
                "    \"id\": null,\n" +
                "    \"title\": \"sandbox_cilai_c的沙箱测试店铺\",\n" +
                "    \"nick\": null,\n" +
                "    \"accessToken\": null,\n" +
                "    \"products\": []\n" +
                "}";
        ContentDecoder decoder = mock(ContentDecoder.class);
        Object returnObject = new HashMap();
        when(decoder.decode(rawString)).thenReturn(returnObject);
        JsonTaobaoApiResult result = new JsonTaobaoApiResult(rawString);
        Object resultObject = result.getObject(decoder);
        Assert.assertEquals(resultObject, resultObject);
    }
}
