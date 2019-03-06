package com.thz.service.impl;

import com.thz.pojo.User;
import com.thz.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.is;

//此单元测试失败，因为无法引入配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:config/applicationContext.xml"}) //classpath:在当前项目查找；classpath*：在所有依赖的jar包的classpath下找
@Transactional
public class UserServiceImplTest {
    @Resource
    private UserService userService;

    @Test
    public void addUser() {
        User user=new User();
        user.setUsername("test");
        user.setPassword("123456");
        int result=userService.addUser(user);
        Assert.assertThat(result,is(1));
    }
}