package com.zyx.web;

import com.zyx.dao.UserMapper;
import com.zyx.entity.Article;
import com.zyx.feign.FeignService;
import com.zyx.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
    @Autowired
    FeignService feignService;

    @Test
    public void reload(){
        List<Article> list = feignService.getAllBlog();
        System.out.println(list);
    }
}