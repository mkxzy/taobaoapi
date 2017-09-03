package com.iblotus;

/**
 * 淘宝返回内容解码器
 * @param <T> 返回类型
 */
public interface ContentDecoder<T> {

    T decode(String rawString);
}