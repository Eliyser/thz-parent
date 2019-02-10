package com.thz.controller;

import com.thz.pojo.User;
import com.thz.service.UserService;
import com.thz.tool.IPUtils;
import com.thz.tool.RandomValidateCodeUtil;
import com.thz.tool.StringUtil;
import com.thz.tool.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @Author haien
 * @Description 此文件用于前端所有页面的请求
 * @Date 2019/1/15
 **/
@Controller
@Transactional
public class WebPageController {
    private static final Logger logger=LoggerFactory.getLogger(WebPageController.class);

    @Resource
    private UserService userService;

    /**
     * @Author haien
     * @Description 映射登录页面
     * @Date 2019/1/15
     * @Param []
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping({"/login"})
    public ModelAndView index(){
        logger.info("----请求登录页面----");
        ModelAndView modelAndView=new ModelAndView("/login");
        return modelAndView;
    }

    /**
     * @Author haien
     * @Description 映射注册页面
     * @Date 2019/2/6
     * @Param []
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("/register")
    public ModelAndView register(){
        logger.info("----请求注册页面----");
        ModelAndView modelAndView=new ModelAndView("/jsp/register");
        return modelAndView;
    }

    /**
     * @Author haien
     * @Description 用户注册处理器（验证码、两次密码是否相同前端自行处理）
     * @Date 2019/2/4
     * @Param [request, response, model]
     * @return java.lang.String
     **/
    @RequestMapping(value="/register_check",method=RequestMethod.POST)
    public String register(HttpServletRequest request, HttpServletResponse response
            , Model model, RedirectAttributes redirectAttributes) throws UnknownHostException {

        //用户名、手机、邮箱是否唯一
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String rpsw=request.getParameter("rpsw");
        String email=request.getParameter("email");
        String phone=request.getParameter("phone");
        String sex=request.getParameter("sex");

        logger.info("----用户注册：username={},phone={},email={},sex={}----"
                ,username,phone,email,sex);

        //存值带回
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        request.setAttribute("rpsw",rpsw);
        request.setAttribute("email",email);
        request.setAttribute("phone",phone);
        request.setAttribute("sex",sex);

        //判空
        if(StringUtil.isNull(username)){
            model.addAttribute("message","请填写用户名");
            return "/jsp/register";
        }
        if(StringUtil.isNull(phone)){
            model.addAttribute("message","请填写手机号");
            return "/jsp/register";
        }
        if(StringUtil.isNull(email)){
            model.addAttribute("message","请填写邮箱");
            return "/jsp/register";
        }
        if(StringUtil.isNull(sex)){
            model.addAttribute("message","请选择性别");
            return "/jsp/register";
        }
        if(StringUtil.isNull(password)){
            model.addAttribute("message","请填写密码");
            return "/jsp/register";
        }

        //正则验证
        if(!ValidatorUtil.isUsername(username)){
            model.addAttribute("message","用户名只能由3~20位的数字、字母或者下划线组成");
            return "/jsp/register";
        }
        if(!ValidatorUtil.isMobile(phone)){
            model.addAttribute("message","手机格式错误");
            return "/jsp/register";
        }
        if(!ValidatorUtil.isEmail(email)){
            model.addAttribute("message","邮箱格式错误");
            return "/jsp/register";
        }
        if(!sex.equals("男")&&!sex.equals("女")){
            model.addAttribute("message","性别选择错误");
            return "/jsp/register";
        }
        if(!ValidatorUtil.isPasswd(password)){
            model.addAttribute("message","密码只能由6~12位的数字或字母组成");
            return "/jsp/register";
        }

        //信息是否已使用过
        User user=userService.findUserByName(username);
        if(user!=null){ //再检查一遍
            model.addAttribute("message","用户名已存在");
            return "/jsp/register";
        }
        user=userService.findUserByPhone(phone);
        if(user!=null){
            model.addAttribute("message","手机已绑定");
            return "/jsp/register";
        }
        user=userService.findUserByEmail(email);
        if(user!=null){
            model.addAttribute("message","邮箱已绑定");
            return "/jsp/register";
        }

        //注册成功
        String ip=IPUtils.getIP(request);
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        user=new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); //bcrypt算法加密
        user.setPhone(phone);
        user.setEmail(email);
        user.setSex(sex);
        user.setRegisterIp(ip);
        user.setRegisterTime(new Date());
        userService.addUser(user);
        logger.info("----注册成功--username={}----",username);
        redirectAttributes.addFlashAttribute("message","注册成功"); //发送属性
        return "redirect:/login"; //重定向(重新发起一次请求，需由controller处理)
    }

    /**
     * @Author haien
     * @Description 异步请求用户名是否已存在
     * @Date 2019/2/4
     * @Param [username]
     * @return boolean
     **/
    @RequestMapping(value = "/isUsernameExist",method = RequestMethod.GET)
    public boolean isUsernameExist(@RequestParam("username") String username){
        return userService.findUserByName(username)==null?false:true;
    }

    /**
     * @Author haien
     * @Description 异步请求手机是否已绑定
     * @Date 2019/2/6
     * @Param [phone]
     * @return boolean
     **/
    public boolean isPhoneBingding(@RequestParam("phone")String phone){
        return userService.findUserByPhone(phone)==null?false:true;
    }

    /**
     * @Author haien
     * @Description 异步请求邮箱是否已绑定
     * @Date 2019/2/6
     * @Param [email]
     * @return boolean
     **/
    public boolean isEmailBinding(@RequestParam("email")String email){
        return userService.findUserByEmail(email)==null?false:true;
    }

    /**
     * @Author haien
     * @Description 获取图片验证码
     * @Date 2019/2/9
     * @Param [request, response]
     * @return void
     **/
    @RequestMapping("/getVerifyCode")
    public void getVerifyCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expire",0);

        String randCode=RandomValidateCodeUtil.getRandomCode(); //获取4位验证码
        HttpSession session=request.getSession();
        session.removeAttribute("RANDOMVALIDATECODEKEY");
        session.setAttribute("RANDOMVALIDATECODEKEY",randCode);
        BufferedImage image=RandomValidateCodeUtil.getImageFromCode(randCode); //生成图片
        logger.info("----获取图片验证码--RANDOMVALIDATECODEKEY={}----",randCode);
        try {
            ImageIO.write(image,"JPEG",response.getOutputStream());
        } catch (IOException e) {
            throw new IOException("获取客户端输出流失败");
        }
    }
}
























