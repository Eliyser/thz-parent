package com.thz.config;

import com.thz.pojo.User;
import com.thz.service.UserService;
import com.thz.tool.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @Author haien
 * @Description 自定义认证成功处理器
 *              为什么不实现接口而是继承SavedRequestAwareAuthenticationSuccessHandler类，
 *              因为这个类记住了上次请求路径，如果你是请求其他页面被拦截到登录页的，
 *              这时候输入用户名和密码点击登录，还会自动跳转到该页面，而不是默认主页。
 * @Date 2019/1/31
 **/
@Component
public class MyAuthenticationSuccessHandler
        extends SavedRequestAwareAuthenticationSuccessHandler {
    private static final Logger logger=LoggerFactory
            .getLogger(MyAuthenticationSuccessHandler.class);

    @Resource
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws ServletException, IOException {

        logger.info("----登录成功，username="+request.getParameter("username")+"----");

        //获取当前用户
        User user=(User)authentication.getPrincipal();
        //获取authentication中的details中的remoteAddress
        user=userService.findUserByName(user.getUsername());
        user.setLoginTime(new Date());
        user.setLoginIp(IPUtils.getIP(request));
        //存入session
        request.getSession().setAttribute("user",user);
        //更新数据库
        userService.updateUser(user);

        //执行父类重定向到原访问路径的方法
        super.onAuthenticationSuccess(request,response,authentication);
    }
}
















