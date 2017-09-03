# taobaoapi

淘宝官方的API实在太难用了，于是自己封装了一个

## 示例
TaobaoApiConfig config = new TaobaoApiConfig("https://gw.api.tbsandbox.com/router/rest",</br>
                "1021035674",</br>
                "sandbox68cb8f35e1d06f8154c1551de");</br>
TaobaoApiMethod method = config.createMethod("taobao.shop.get")</br>
                .setParam("fields", ApiParams.arrayParam("sid", "cid"))</br>
                .setParam("nick", ApiParams.stringParam("sandbox_cilai_c"));</br>
TaobaoApiResult result = method.call();</br>
System.out.println(result.toString());</br>
