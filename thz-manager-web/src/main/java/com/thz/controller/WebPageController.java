package com.thz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author haien
 * @Description 此文件用于前端所有页面的请求
 * @Date 2019/1/15
 **/
@Controller
public class WebPageController {
    private static final Logger logger=LoggerFactory.getLogger(WebPageController.class);

    /**
     * @Author haien
     * @Description 映射默认首页
     * @Date 2019/1/15
     * @Param []
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping({"/","/index.html","/index","/main","/main.html"})
    public ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView("/index");
        return modelAndView;
    }
}
