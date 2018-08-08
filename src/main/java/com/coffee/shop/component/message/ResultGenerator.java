package com.coffee.shop.component.message;

/**
 * 响应结果生成工具
 * @author waylon
 * @date 2017/12/25
 **/
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    /**
     * 无参数的请求成功结果
     * @return
     */
    public static Result genSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    /**
     * 带参数的请求成功结果
     * @param data
     * @return
     */
    public static Result genSuccessResult(Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    /**
     * 请求失败结果
     * @param message
     * @return
     */
    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

    /**
     * 重定向结果
     * @param message
     * @return
     */
    public static Result genRedirectResult(String message){
        return new Result()
                .setCode(ResultCode.REDIRECT)
                .setMessage(message);
    }



}
