package com.zyx.web;

import com.zyx.dao.UserMapper;
import com.zyx.entity.User;
import com.zyx.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by YuXingZh on 19-3-31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminControllerTest {
    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Test
    public void reload(){
        User user = new User("1","YuXingZh","zhengyuxing","617058979@qq.com");
        User user1 = userMapper.selectOne(user);
        System.out.println(user1);
    }
}