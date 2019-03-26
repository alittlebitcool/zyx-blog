package com.zyx.service.impl;

import com.zyx.dao.UserMapper;
import com.zyx.entity.User;
import com.zyx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by YuXingZh on 19-3-26
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    UserMapper userMapper;

    @Override
    public User checkUser(User user) {
        return userMapper.checkUser(user);
    }

}