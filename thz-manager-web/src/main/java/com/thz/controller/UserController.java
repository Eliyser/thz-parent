package com.thz.controller;

import com.thz.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    //日志
    public static final Logger logger=LoggerFactory.getLogger(GoodsController.class);

    @Resource
    private UserService userService;


}


























