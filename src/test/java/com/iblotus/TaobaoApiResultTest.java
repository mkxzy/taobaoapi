package com.iblotus;

import org.junit.Assert;
import org.junit.Test;

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
        TaobaoApiResult result = new TaobaoApiResult(rawString);
        Assert.assertEquals(rawString, result.toString());
    }
}
