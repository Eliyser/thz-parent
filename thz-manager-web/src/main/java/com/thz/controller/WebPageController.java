package com.thz.controller;

import com.alibaba.fastjson.JSONObject;
import com.thz.pojo.User;
import com.thz.service.UserService;
import com.thz.tool.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author haien
 * @Description 此文件用于前端所有页面的请求
 * @Date 2019/1/15
 **/
@Controller
@Transactional
public class WebPageController {
    private static final Logger logger = LoggerFactory.getLogger(WebPageController.class);

    //存在session中的验证码key
    private static final String VERIFYCODEKEY="VERIFYCODEKEY";
    //存在session中的验证码生成时间
    private static final String CODECREATETIMEKEY="CODECREATETIME";
    //以上两个字段拼成json，存在session中的key
    private static final String VERIFYMSG="VERIFYMSG";

    @Resource
    private UserService userService;

    //BCrypt加密算法
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Author haien
     * @Description 映射登录页面
     * @Date 2019/1/15
     * @Param []
     **/
    @RequestMapping("/login")
    public ModelAndView index(@RequestParam(value = "error",required = false,
            defaultValue = "0")String error) {
        logger.info("----请求登录页面----");
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("error",error); //底层是放进request里
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Author haien
     * @Description 映射注册页面
     * @Date 2019/2/6
     * @Param []
     **/
    @RequestMapping("/register")
    public ModelAndView register() {
        logger.info("----请求注册页面----");
        ModelAndView modelAndView = new ModelAndView("/jsp/register");
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Author haien
     * @Description 映射找回密码页面
     * @Date 2019/2/10
     * @Param []
     **/
    @RequestMapping("/forgetPwd")
    public ModelAndView forgetPwd() {
        logger.info("----请求找回密码页面----");
        ModelAndView modelAndView = new ModelAndView("/jsp/forget");
        return modelAndView;
    }

    /**
     * @return java.lang.String
     * @Author haien
     * @Description 用户注册处理器（两次密码是否相同前端自行处理）
     * @Date 2019/2/4
     * @Param [request, response, model]
     **/
    @RequestMapping(value = "/register_check", method = RequestMethod.POST)
    public String register(HttpServletRequest request, HttpServletResponse response
            , Model model, RedirectAttributes redirectAttributes) throws UnknownHostException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rpsw = request.getParameter("rpsw");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("sex");
        String verifyCode=request.getParameter("verifyCode");

        logger.info("----用户注册：username={},phone={},email={},sex={},verifyCode={}----"
                , username, phone, email, sex, verifyCode);

        //存值带回
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.setAttribute("rpsw", rpsw);
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);
        request.setAttribute("sex", sex);

        //判空
        if (StringUtil.isNull(username)) {
            model.addAttribute("message", "请填写用户名");
            return "/jsp/register";
        }
        if (StringUtil.isNull(phone)) {
            model.addAttribute("message", "请填写手机号");
            return "/jsp/register";
        }
        if (StringUtil.isNull(email)) {
            model.addAttribute("message", "请填写邮箱");
            return "/jsp/register";
        }
        if (StringUtil.isNull(sex)) {
            model.addAttribute("message", "请选择性别");
            return "/jsp/register";
        }
        if (StringUtil.isNull(password)) {
            model.addAttribute("message", "请填写密码");
            return "/jsp/register";
        }
        if (StringUtil.isNull(rpsw)) {
            model.addAttribute("message", "请确认密码");
            return "/jsp/register";
        }
        if(StringUtil.isNull(verifyCode)){
            model.addAttribute("message", "请填写验证码");
            return "/jsp/register";
        }

        //正则验证
        if (!ValidatorUtil.isUsername(username)) {
            model.addAttribute("message", "用户名只能由3~20位的数字、字母或者下划线组成");
            return "/jsp/register";
        }
        if (!ValidatorUtil.isMobile(phone)) {
            model.addAttribute("message", "手机格式错误");
            return "/jsp/register";
        }
        if (!ValidatorUtil.isEmail(email)) {
            model.addAttribute("message", "邮箱格式错误");
            return "/jsp/register";
        }
        if (!sex.equals("男") && !sex.equals("女")) {
            model.addAttribute("message", "性别选择错误");
            return "/jsp/register";
        }
        if (!ValidatorUtil.isPasswd(password)) {
            model.addAttribute("message", "密码只能由6~12位的数字或字母组成");
            return "/jsp/register";
        }

        //信息是否已使用过
        if (isUsernameExist(username)) { //再检查一遍
            model.addAttribute("message", "用户名已存在");
            return "/jsp/register";
        }
        if (isPhoneBingding(phone)) {
            model.addAttribute("message", "手机已绑定");
            return "/jsp/register";
        }
        if (isEmailBinding(email)) {
            model.addAttribute("message", "邮箱已绑定");
            return "/jsp/register";
        }

        //验证码是否正确
        JSONObject verifyMsg=(JSONObject)request.getSession().getAttribute(VERIFYMSG);
        String originCode=verifyMsg.getString(VERIFYCODEKEY);
        long createTime=verifyMsg.getLong(CODECREATETIMEKEY);
        if(!userService.isVrfCodeRight(verifyCode,originCode,createTime)) {
            model.addAttribute("message", "验证码失效");
            return "/jsp/register";
        }

        //注册成功
        String ip = IPUtils.getIP(request);
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); //bcrypt算法加密
        user.setPhone(phone);
        user.setEmail(email);
        user.setSex(sex);
        user.setRegisterIp(ip);
        user.setRegisterTime(new Date());
        userService.addUser(user);

        logger.info("----注册成功--username={}----", username);
        redirectAttributes.addFlashAttribute("message", "注册成功"); //发送属性
        return "redirect:/login"; //重定向(重新发起一次请求，需由controller处理)
    }

    /**
     * @return boolean
     * @Author haien
     * @Description 异步请求用户名是否已存在
     * @Date 2019/2/4
     * @Param [username]
     **/
    @ResponseBody
    @RequestMapping(value = "/isUsernameExist", method = RequestMethod.GET)
    public boolean isUsernameExist(@RequestParam("username") String username) {
        return userService.findUserByName(username) == null ? false : true;
    }

    /**
     * @return boolean
     * @Author haien
     * @Description 异步请求手机是否已绑定
     * @Date 2019/2/6
     * @Param [phone]
     **/
    @ResponseBody
    @RequestMapping(value = "/isPhoneBingding", method = RequestMethod.GET)
    public boolean isPhoneBingding(@RequestParam("phone") String phone) {
        return userService.findUserByPhone(phone) == null ? false : true;
    }

    /**
     * @return boolean
     * @Author haien
     * @Description 异步请求邮箱是否已绑定
     * @Date 2019/2/6
     * @Param [email]
     **/
    @ResponseBody
    @RequestMapping(value = "/isEmailBinding", method = RequestMethod.GET)
    public boolean isEmailBinding(@RequestParam("email") String email) {
        return userService.findUserByEmail(email) == null ? false : true;
    }

    /**
     * @return void
     * @Author haien
     * @Description 发送短信验证码
     * @Date 2019/2/9
     * @Param [request, response]
     **/
    @ResponseBody
    @RequestMapping("/getVerifyCode")
    public Map<String, String> getVerifyCode(HttpServletRequest request
            , HttpServletResponse response) throws IOException {

        Map<String, String> result = new HashMap<>();

        String phone = request.getParameter("phone");
        if (StringUtil.isNull(phone)) {
            result.put("message", "请填写手机号");
            return result;
        }
        if (!ValidatorUtil.isMobile(phone)) {
            result.put("message", "手机格式错误");
            return result;
        }
        if (isPhoneBingding(phone)) {
            result.put("message", "手机已绑定");
            return result;
        }

        String code = RandomValidateCodeUtil.getVerifyCode();
        logger.info("----请求验证码--code={}----", code);
        try {
            int success = SmsUtil.sendCode(phone, code);
        } catch (NoSuchAlgorithmException e) {
            logger.info("----短信发送失败----");
            throw new NoSuchFieldError("生成信息摘要异常：不存在指定的加密算法");
        }
        logger.info("----短信发送成功----");
        JSONObject json = new JSONObject();
        json.put(VERIFYCODEKEY, code);
        json.put(CODECREATETIMEKEY, System.currentTimeMillis());
        request.getSession().setAttribute(VERIFYMSG, json);
        result.put("message", "短信发送成功");
        return result;
    }

    /**
     * @Author haien
     * @Description 找回密码验证
     * @Date 2019/2/11
     * @Param [request, Response]
     * @return void
     **/
    @RequestMapping(value = "/forgetPwd_check", method = RequestMethod.POST)
    public String forgetPwdCheck(HttpServletRequest request, Model model
            , HttpServletResponse response, RedirectAttributes redirectAttributes){

        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        String verifyCode=request.getParameter("verifyCode");
        String password = request.getParameter("password");
        String rpsw = request.getParameter("rpsw");

        logger.info("----找回密码：username={},phone={}----", username, phone);

        //存值带回
        request.setAttribute("username", username);
        request.setAttribute("phone", phone);
        request.setAttribute("password", password);
        request.setAttribute("rpsw", rpsw);

        //判空
        if (StringUtil.isNull(username)) {
            model.addAttribute("message", "请填写用户名");
            return "/jsp/forget";
        }
        if (StringUtil.isNull(phone)) {
            model.addAttribute("message", "请填写手机");
            return "/jsp/forget";
        }
        if(StringUtil.isNull(verifyCode)){
            model.addAttribute("message", "请填写验证码");
            return "/jsp/forget";
        }
        if (StringUtil.isNull(password)) {
            model.addAttribute("message", "请填写密码");
            return "/jsp/forget";
        }

        User user=userService.findUserByName(username);
        if (null==user) {
            model.addAttribute("message", "用户名不存在");
            return "/jsp/forget";
        }
        if(!user.getPhone().equals(phone)){
            model.addAttribute("message", "手机号与原先绑定号码不一致");
            return "/jsp/forget";
        }
        if (!ValidatorUtil.isPasswd(password)) {
            model.addAttribute("message", "密码只能由6~12位的数字或字母组成");
            return "/jsp/forget";
        }
        //验证码是否正确
        JSONObject verifyMsg=(JSONObject)request.getSession().getAttribute(VERIFYMSG);
        String originCode=verifyMsg.getString(VERIFYCODEKEY);
        long createTime=verifyMsg.getLong(CODECREATETIMEKEY);
        if(!userService.isVrfCodeRight(verifyCode,originCode,createTime)) {
            model.addAttribute("message", "验证码失效");
            return "/jsp/forget";
        }

        user.setPassword(passwordEncoder.encode(password)); //bcrypt算法加密
        userService.updateUser(user);

        logger.info("----找回密码成功--username={}----", username);
        redirectAttributes.addFlashAttribute("message", "密码设置成功"); //发送属性
        return "redirect:/login"; //重定向(重新发起一次请求，需由controller处理)
    }
}
























