package com.thz.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author haien
 * @Description 自定义失败处理器
 * @Date 2019/2/14
 **/
@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final Logger logger=LoggerFactory
            .getLogger(MyAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
           HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        logger.info("----登录失败----");

        //存值带回
        String username=request.getParameter("username");
        request.setAttribute("username",username);

        response.setContentType("text/html;charset=UTF-8");
        /* 调用父类重定向方法，但是request中的username会丢失，就只能放在url里传过去
        super.setDefaultFailureUrl("/login.jsp?error=1&username="+username);
        super.onAuthenticationFailure(request, response, exception);*/
        //不然就自己转发，request就不会丢失
        request.getRequestDispatcher("/login?error=1")
                .forward(request, response);
    }
}
