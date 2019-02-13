package com.thz.controller;

import com.alibaba.fastjson.JSONObject;
import com.thz.exception.ArgumentIllegalException;
import com.thz.exception.AuthenticationException;
import com.thz.pojo.User;
import com.thz.service.UserService;
import com.thz.tool.StringUtil;
import com.thz.tool.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    //日志
    public static final Logger logger=LoggerFactory.getLogger(GoodsController.class);

    @Resource
    private UserService userService;

    //存在session中的验证码key
    private static final String VERIFYCODEKEY="VERIFYCODEKEY";
    //存在session中的验证码生成时间
    private static final String CODECREATETIMEKEY="CODECREATETIME";
    //以上两个字段拼成json，存在session中的key
    private static final String VERIFYMSG="VERIFYMSG";

    /**
     * @Author haien
     * @Description 映射修改信息页面
     * @Date 2019/2/12
     * @Param []
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("/modifyMsg")
    public ModelAndView modifyMsg(HttpServletRequest request) throws AuthenticationException {
        logger.info("----请求个人信息页面----");

        User user=(User)request.getSession().getAttribute("user");
        if(null==user){
            throw new AuthenticationException("用户未登录");
        }
        request.setAttribute("phone",user.getPhone());
        request.setAttribute("email",user.getEmail());

        ModelAndView modelAndView = new ModelAndView("/jsp/security");
        return modelAndView;
    }

    /**
     * @Author haien
     * @Description 用户修改个人信息：手机、邮箱、密码（两次密码是否相同需要前端自行判断）
     * @Date 2019/2/12
     * @Param [password, userId]
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    @RequestMapping("/modifyMsg_check")
    public Map<String,String> modifyMsg(HttpServletRequest request, HttpServletResponse response)
            throws MissingServletRequestParameterException, ArgumentIllegalException, AuthenticationException {

        User user=(User)request.getSession().getAttribute("user");
        if(null==user){
            throw new AuthenticationException("用户未登录");
        }
        int userId=user.getId();
        logger.info("----修改信息--userId={}----", userId);
        if(userId<=0||(user=userService.findUserById(userId))==null){
            throw new ArgumentIllegalException("参数userId不合法");
        }

        request.setAttribute("phone",user.getPhone());
        request.setAttribute("email",user.getEmail());

        String phone = request.getParameter("phone");
        String email=request.getParameter("email");
        String verifyCode=request.getParameter("verifyCode");
        String password = request.getParameter("password");
        String rpsw = request.getParameter("rpsw");

        logger.info("----保存信息--phone={},email={},verifyCode={}----", phone, email, verifyCode);

        Map<String,String> result=new HashMap<>();
        //判空
        if (StringUtil.isNull(phone)) {
            result.put("message", "请填写手机");
            return result;
        }
        if (StringUtil.isNull(email)) {
            result.put("message", "请填写邮箱");
            return result;
        }
        if (StringUtil.isNull(password)) {
            result.put("message", "请填写密码");
            return result;
        }
        if (StringUtil.isNull(rpsw)) {
            result.put("message", "请确认密码");
            return result;
        }
        if(StringUtil.isNull(verifyCode)){
            result.put("message", "请填写验证码");
            return result;
        }

        //正则验证
        if (!ValidatorUtil.isMobile(phone)) {
            result.put("message", "手机格式错误");
            return result;
        }
        if (!ValidatorUtil.isEmail(email)) {
            result.put("message", "邮箱格式错误");
            return result;
        }
        if (!ValidatorUtil.isPasswd(password)) {
            result.put("message", "密码只能由6~12位的数字或字母组成");
            return result;
        }

        //信息是否已使用过
        user=userService.findUserByPhone(phone);
        if ( user!= null && user.getId()!=userId) {
            result.put("message", "手机已绑定");
            return result;
        }
        user=userService.findUserByEmail(email);
        if ( user!= null && user.getId()!=userId) {
            result.put("message", "邮箱已绑定");
            return result;
        }

        //验证码是否正确
        JSONObject verifyMsg=(JSONObject)request.getSession().getAttribute(VERIFYMSG);
        String originCode=verifyMsg.getString(VERIFYCODEKEY);
        long createTime=verifyMsg.getLong(CODECREATETIMEKEY);
        if(!userService.isVrfCodeRight(verifyCode,originCode,createTime)) {
            result.put("message", "验证码失效");
            return result;
        }

        //修改成功
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        userService.updateUser(user);

        logger.info("----保存成功--userId={}----", userId);
        result.put("message", "保存成功");
        return result;
    }
}


























