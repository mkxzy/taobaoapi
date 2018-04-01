## 背景

淘宝官方的API实在太难用了，于是自己封装了一个<br>
[淘宝API文档](https://open.taobao.com/docs/api_list.htm?spm=a219a.7386653.1.22.WbbOOb)

## 安装
```maven
<dependency>
    <groupId>com.iblotus</groupId>
    <artifactId>taobao-api</artifactId>
    <version>1.0</version>
</dependency>
```

## 调用示例

```java
public class StoreDecoder implements ContentDecoder<Store> {

    @Override
    public Store decode(String rawString) {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(rawString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Store store = new Store();
        System.out.println(jsonNode.toString());
        String title = jsonNode.get("shop_get_response")
                .get("shop")
                .get("title")
                .asText();
        store.setTitle(title);
        return store;
    }
}
```

### 调用方式一
```java
TaobaoApiConfig config = new TaobaoApiConfig("https://gw.api.tbsandbox.com/router/rest",
                "1021035674",
                "sandbox68cb8f35e1d06f8154c1551de");
config.addDecoder("taobao.shop.get", new StoreDecoder());

TaobaoApiMethod method = config.createMethod("taobao.shop.get")
                .setParam("fields", ApiParams.arrayParam("sid", "cid"))
                .setParam("nick", ApiParams.stringParam("sandbox_cilai_c"));
TaobaoApiResult result = method.call();
System.out.println(result.toString());
System.out.println(result.getObject());
```

### 调用方式二
```java
TaobaoApiConfig config = new TaobaoApiConfig("https://gw.api.tbsandbox.com/router/rest",
                "1021035674",
                "sandbox68cb8f35e1d06f8154c1551de");
                
TaobaoApiMethod method = config.createMethod("taobao.shop.get")
                .setParam("fields", ApiParams.arrayParam("sid", "cid"))
                .setParam("nick", ApiParams.stringParam("sandbox_cilai_c"));
TaobaoApiResult result = method.call();
System.out.println(result.toString());
System.out.println(result.getObject(new StoreDecoder()));
```
