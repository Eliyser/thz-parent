package com.thz.config;

import com.thz.tool.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author haien
 * @Description controller层全局异常处理
 * @Date 2018/12/1
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger=LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value=Exception.class) //处理Exception层Exception及其子类
    public void defaultHandler(HttpServletResponse response, Exception e){
        logger.error(e.getMessage(),e);
        //生成错误报文
        ServletUtil.createErrorResponse(500,null,null,e.getMessage(),response);
    }
}


























