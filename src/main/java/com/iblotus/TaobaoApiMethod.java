package com.iblotus;

import java.io.IOException;

/**
 * 淘宝抽象接口
 * 负责处理方法和参数
 */
public interface TaobaoApiMethod {

    TaobaoApiMethod setParam(String name, ApiParam value);

    TaobaoApiMethod setAccessToken(String accessToken);

    TaobaoApiResult call() throws IOException;
}
