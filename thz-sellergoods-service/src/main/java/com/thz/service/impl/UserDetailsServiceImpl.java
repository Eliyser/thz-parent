package com.thz.service.impl;

import com.thz.pojo.User;
import com.thz.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author haien
 * @Description 自定义用户验证
 * @Date 2019/1/23
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger=LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("-----自定义用户服务----查询用户---username="+username);

        //通过用户名查询用户
        User user=userService.findUserByName(username);

        if(null==user)
            throw new UsernameNotFoundException(username+"not exist!");

        return user;
    }
}


























