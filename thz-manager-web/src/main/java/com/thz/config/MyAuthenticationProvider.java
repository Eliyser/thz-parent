package com.thz.config;

import com.thz.pojo.User;
import com.thz.service.impl.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    private static final Logger logger=LoggerFactory.getLogger(MyAuthenticationProvider.class);

    @Resource
    private UserDetailsServiceImpl myUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        String password=(String)authentication.getCredentials();

        logger.info("自定义用户验证：username="+username+",password="+password);

        User user=(User)myUserDetailsService.loadUserByUsername(username);

        if(null==user) throw new BadCredentialsException("Username not found!");

        //加密过程在这里体现
        /*System.out.println("username="+username+"form's password="
                +password+"db's password="+user.getPassword());*/
        //password:明文,user.getPassword():密文,但WebSecurityConfig里添加了passwordEncoder，两者就能对应起来
        if(!password.equals(user.getPassword()))
        throw new BadCredentialsException("Wrong password!");

        return new UsernamePasswordAuthenticationToken(user,password,null);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

























