package com.thz.exception;

/**
 * @Author haien
 * @Description 参数不合法异常
 * @Date 2019/2/12
 **/
public class ArgumentIllegalException extends Exception{
    public ArgumentIllegalException() {
    }

    public ArgumentIllegalException(String message) {
        super(message);
    }

    public ArgumentIllegalException(String message, Throwable cause) {
        super(message, cause);
    }
}





















