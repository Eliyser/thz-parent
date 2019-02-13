package com.thz.config;

import com.thz.tool.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author haien
 * @Description controller层全局异常处理
 * @Date 2018/12/1
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger=LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value=Exception.class) //处理Exception层Exception及其子类
    // @ResponseBody
    public void defaultHandler(HttpServletResponse response, Exception e){
        String message=e.getMessage();
        logger.error(message,e);
        //生成错误报文
        if(null==message)   message=e.getClass().getName(); //系统异常无描述则用异常类名作为描述
        ServletUtil.createErrorResponse(500,500,null,message,response);
    }
}


























