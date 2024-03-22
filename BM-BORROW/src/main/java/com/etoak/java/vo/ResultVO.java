package com.etoak.java.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * REST接口统一返回值
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {

    /**
     * 接口调用成功之后的默认响应码
     */
    public static final int SUCCESS_CODE = 200;

    /**
     * 接口调用成功之后的默认响应消息
     */
    public static final String SUCCESS_MSG = "success";

    /**
     * 接口调用失败之后的默认响应码
     */
    public static final int FAILED_CODE = 500;

    /**
     * 接口调用失败之后的默认响应消息
     */
    public static final String FAILED_MSG = "系统异常！";

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 接口执行成功之后调用的方法
     */
    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static <T> ResultVO<T> failed() {
        return failed(FAILED_MSG);
    }

    public static <T> ResultVO<T> failed(String msg) {
        return failed(FAILED_CODE, msg);
    }

    public static <T> ResultVO<T> failed(int code, String msg) {
        return new ResultVO<>(code, msg, null);
    }

}
