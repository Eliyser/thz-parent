package com.thz.dao;

import com.thz.pojo.User;

/**
 * @Author haien
 * @Description 用户dao接口
 * @Date 2019/1/23
 **/
public interface UserDao {
    public User findUserByName(String username);

    public int updateUser(User user);

    public User findUserByPhone(String phone);

    public User findUserByEmail(String email);

    public int insertUser(User user);

    public User findUserById(int id);
}
