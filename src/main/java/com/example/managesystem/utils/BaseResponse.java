package com.example.managesystem.utils;

import lombok.Data;


@Data
public class BaseResponse<T> {

    private int code = 200;
    private String msg;
    private T data;

    public BaseResponse() {
    }

    private static final BaseResponse SUCCESS_RESULT = ok("success");

    /**
     * 默认成功返回
     *
     * @return
     */
    public static BaseResponse ok() {
        return SUCCESS_RESULT;
    }

    /**
     * 含有返回数据的成功返回
     *
     * @param data
     * @return
     */
    public static BaseResponse ok(Object data) {
        BaseResponse result = new BaseResponse();
        result.setCode(200);
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    /**
     * 含有错误码及自定义错误信息的错误返回
     *
     * @param code
     * @param msg
     * @return
     */
    public static BaseResponse fail(int code, String msg) {
        BaseResponse result = new BaseResponse();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 自定义错误信息的错误返回
     *
     * @param msg
     * @return
     */
    public static BaseResponse fail(String msg) {
        BaseResponse result = new BaseResponse();
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }

}
