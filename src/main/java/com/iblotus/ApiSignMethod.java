package com.iblotus;

import java.util.Map;

/**
 * Created by xiezhiyan on 17-9-3.
 * 签名算法
 */
interface ApiSignMethod {

    String sign(Map<String, String> params);
}