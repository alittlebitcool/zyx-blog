package com.zyx.dao;

import com.zyx.entity.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by YuXingZh on 19-3-26
 */
public interface UserMapper extends Mapper<User> {
    public User checkUser(User user);
}
