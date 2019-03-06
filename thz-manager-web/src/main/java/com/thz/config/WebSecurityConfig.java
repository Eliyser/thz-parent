package com.thz.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity //启用web安全功能
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger=LoggerFactory.getLogger(WebSecurityConfig.class);

    //自定义用户服务
    /*@Bean
    private UserDetailsService myUserDetailsService(){
        return new UserDetialsServiceImpl();
    }*/
    @Resource
    private UserDetailsService myUserDetailsService;

    //自定义用户验证
    @Resource
    private AuthenticationProvider myAuthenticationProvider;

    //自定义认证成功处理器
    @Resource
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    //自定义认证失败处理器
    @Resource
    private AuthenticationFailureHandler myAuthenticationFailureHandler;

    /**
     * 密码加密
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * @Author haien
     * @Description 添加自定义的用户服务
     * @Date 2019/1/23
     * @Param [authenticationManagerBuilder]
     * @return void
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
        throws Exception{
        //auth.userDetailsService(myUserDetailsService());
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder()); //应该是把拦截到的表单密码加密
        //auth.authenticationProvider(myAuthenticationProvider); //本项目用不上自定义用户验证
    }
/*

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        logger.info("----自定义用户验证----");
        auth.authenticationProvider(myAuthenticationProvider);
    }
*/

    /**
     * @Author haien
     * @Description 解决静态资源被拦截问题
     * @Date 2019/1/23
     * @Param [web]
     * @return void
     **/
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/images/**","/js/**"
                ,"/css/**","/**/register");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable() //关闭跨域请求
                .authorizeRequests() //需要权限
                .antMatchers("/user/**","/goods/**","**/add/**","**/update/**","**/save/**")
                .authenticated() //需要验证
                .and()
                //自定义登录页面
                .formLogin().loginPage("/login.jsp")
                .failureUrl("/login.jsp?error=1")
                .failureForwardUrl("/login.jsp?error=1")
                .loginProcessingUrl("/spring_security_check")
                //.usernameParameter("username") //4.x表单参数已定义为username、password
                //.passwordParameter("password")
                .successHandler(myAuthenticationSuccessHandler) //自定义认证成功处理器，主要是保存信息到库
                .failureHandler(myAuthenticationFailureHandler)
                .permitAll()
                //.defaultSuccessUrl("/jsp/index.jsp")
                .and()
                //配置session管理
                .sessionManagement()
                .maximumSessions(1) //配合HttpSessionEventPublisher（清理掉过期的session）
                .expiredUrl("/login.jsp?expired=1");
        //自定义登出页面
        http
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login.jsp?error=logout")
                .invalidateHttpSession(true)
                .permitAll(); //注销请求可直接访问
    }
}


























