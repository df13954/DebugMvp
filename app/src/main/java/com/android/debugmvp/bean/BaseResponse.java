package com.android.debugmvp.bean;

/**
 * @author dr
 * @date 2020-05-22
 * @description 请求响应基类, 具体的响应继承这个类即可
 */
public class BaseResponse<T> {
    /**
     * "success": true,
     * "code": 10000,
     * "message": "获取分类成功.",
     * "data": 可能是list,可能是单独bean
     */
    private boolean success;
    private int code;
    private String message;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
