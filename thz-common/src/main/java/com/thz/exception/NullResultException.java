package com.thz.exception;

/**
 * @Author haien
 * @Description 响应结果为空异常
 * @Date 2018/12/1
 **/
public class NullResultException extends Exception{
    public NullResultException(String message) {
        super(message);
    }

    public NullResultException(String message, Throwable cause) {
        super(message, cause);
    }
}
