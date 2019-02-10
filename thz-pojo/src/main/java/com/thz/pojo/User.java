package com.thz.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @Author haien
 * @Description 系统用户类
 * @Date 2018/11/26
 **/
public class User implements Serializable,UserDetails {
    private int id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date registerTime; //注册时间
    private String status="valid"; //用户状态：valid有效，invalid删除或封禁
    private String registerIp; //注册ip备案
    private String loginIp; //最近登录ip备案
    private Date loginTime; //最近登录时间
    private String sex; //性别

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @Author haien
     * @Description 获取系统权限
     * @Date 2019/1/23
     * @Param []
     * @return java.util.Collection<? extends org.springframework.security.core.GrantedAuthority>
     **/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; //本项目不实现权限管理
    }

    /**
     * @Author haien
     * @Description 账号不过期？
     * @Date 2019/1/23
     * @Param []
     * @return boolean
     **/
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @Author haien
     * @Description 账号不锁定？
     * @Date 2019/1/23
     * @Param []
     * @return boolean
     **/
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @Author haien
     * @Description 凭证不过期？
     * @Date 2019/1/23
     * @Param []
     * @return boolean
     **/
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @Author haien
     * @Description 账号有效？
     * @Date 2019/1/23
     * @Param []
     * @return boolean
     **/
    @Override
    public boolean isEnabled() {
        return status.equals("valid");
    }

}