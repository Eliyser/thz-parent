package com.thz.service;

import com.thz.pojo.User;

/**
 * @Author haien
 * @Description 用户service接口
 * @Date 2019/1/31
 **/
public interface UserService {
    /**
     * @Author haien
     * @Description 根据username查询用户
     * @Date 2019/1/31
     * @Param [username]
     * @return com.thz.pojo.User
     **/
    public User findUserByName(String username);

    /**
     * @Author haien
     * @Description 更新用户所有信息
     * @Date 2019/1/31
     * @Param [user]
     * @return int
     **/
    public int updateUser(User user);

    /**
     * @Author haien
     * @Description 根据手机查找用户（判断手机是否已绑定）
     * @Date 2019/2/4
     * @Param [phone]
     * @return com.thz.pojo.User
     **/
    public User findUserByPhone(String phone);

    /**
     * @Author haien
     * @Description 根据邮箱查找用户（判断邮箱是否已绑定）
     * @Date 2019/2/4
     * @Param [email]
     * @return com.thz.pojo.User
     **/
    public User findUserByEmail(String email);

    /**
     * @Author haien
     * @Description 添加用户
     * @Date 2019/2/6
     * @Param [user]
     * @return int
     **/
    public int addUser(User user);
}
