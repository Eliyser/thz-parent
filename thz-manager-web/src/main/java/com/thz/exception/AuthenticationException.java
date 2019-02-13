package com.thz.exception;

/**
 * @Author haien
 * @Description 用户登录异常
 * @Date 2019/2/12
 * @Param
 * @return
 **/
public class AuthenticationException extends Exception{
    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
