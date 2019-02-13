package com.thz.config;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author haien
 * @Description 自定义认证失败处理器
 * @Date 2019/2/13
 **/
@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final Logger logger=LoggerFactory
            .getLogger(MyAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request
            , HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        logger.info("----登录失败----");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        JSONObject result=new JSONObject();
        result.put("message","用户名或密码错误");

        PrintWriter printWriter=null;
        String jsonString="";
        try { //必须捕获，不能抛出，因为要保证资源被关闭
            printWriter=response.getWriter();
            jsonString = JSONObject.toJSONString(result);
            printWriter.write(jsonString);
            printWriter.flush();
        } catch (IOException e) {
            logger.error("Get response writer failed!",e); //不用再抛出，因为抛出了就要再生成一次报文，但既然getWrite都出错了生成的报文又怎么打印到前端呢
        }finally {
            if(null!=printWriter)
                printWriter.close();
        }
    }
}
























