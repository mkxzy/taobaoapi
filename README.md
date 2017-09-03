# taobaoapi

淘宝官方的API实在太难用了，于是自己封装了一个

## 示例

```java
TaobaoApiConfig config = new TaobaoApiConfig("https://gw.api.tbsandbox.com/router/rest",
                "1021035674",
                "sandbox68cb8f35e1d06f8154c1551de");
TaobaoApiMethod method = config.createMethod("taobao.shop.get")
                .setParam("fields", ApiParams.arrayParam("sid", "cid"))
                .setParam("nick", ApiParams.stringParam("sandbox_cilai_c"));
TaobaoApiResult result = method.call();
System.out.println(result.toString());
```
