package com.thz.service.impl;

import com.thz.dao.UserDao;
import com.thz.pojo.User;
import com.thz.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * @Author haien
     * @Description 根据username查询用户
     * @Date 2019/1/31
     * @Param [username]
     * @return com.thz.pojo.User
     **/
    @Override
    public User findUserByName(String username) {
        return userDao.findUserByName(username);
    }

    /**
     * @Author haien
     * @Description 更新用户所有信息
     * @Date 2019/1/31
     * @Param [user]
     * @return int
     **/
    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    /**
     * @Author haien
     * @Description 根据手机查找用户（判断手机是否已绑定）
     * @Date 2019/2/4
     * @Param [phone]
     * @return com.thz.pojo.User
     **/
    @Override
    public User findUserByPhone(String phone) {
        return userDao.findUserByPhone(phone);
    }

    /**
     * @Author haien
     * @Description 根据邮箱查找用户（判断邮箱是否已绑定）
     * @Date 2019/2/4
     * @Param [email]
     * @return com.thz.pojo.User
     **/
    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    /**
     * @Author haien
     * @Description 新增用户
     * @Date 2019/2/6
     * @Param [user]
     * @return int
     **/
    @Override
    public int addUser(User user) {
        return userDao.insertUser(user);
    }

    /**
     * @Author haien
     * @Description 根据id查找用户
     * @Date 2019/2/12
     * @Param [id]
     * @return com.thz.pojo.User
     **/
    @Override
    public User findUserById(int id) {
        return userDao.findUserById(id);
    }

    /**
     * @Author haien
     * @Description 比对验证码是否有效
     * @Date 2019/2/12
     * @Param [verifyCode, request]
     * @return boolean
     **/
    @Override
    public boolean isVrfCodeRight(String verifyCode, String originCode,long createTime) {
        if (!originCode.equals(verifyCode)) {
            return false;
        }
        if (System.currentTimeMillis() - createTime > 1000 * 60 * 5) { //5分钟
            return false;
        }
        return true;
    }

}
