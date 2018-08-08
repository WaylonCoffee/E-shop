package com.coffee.shop.component.message;


/**
 * 响应码枚举，参考HTTP状态码的语义
 * @author waylon
 * @date 2017/12/25
 **/
public enum ResultCode {
    SUCCESS(200),//成功
    FAIL(400),//失败
    UNAUTHORIZED(401),//未认证（签名错误）
    NOT_FOUND(404),//接口不存在
    TOO_MANY_REQUEST(429),
    INTERNAL_SERVER_ERROR(500),//服务器内部错误
    REDIRECT(308);//自定义重定向



    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
