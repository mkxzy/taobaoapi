package com.iblotus;

/**
 * 淘宝API调用异常
 */
public class TaobaoApiException extends RuntimeException {

    private final String code;
    private final String msg;
    private final String requestId;

    public TaobaoApiException(String code, String msg, String requestId){

        super(String.format("%s: %s", code, msg));
        this.code = code;
        this.msg = msg;
        this.requestId = requestId;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getRequestId() {
        return requestId;
    }
}
