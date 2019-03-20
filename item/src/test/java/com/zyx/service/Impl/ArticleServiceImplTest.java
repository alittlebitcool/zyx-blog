package com.zyx.service.Impl;

import com.zyx.entity.Article;
import com.zyx.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by YuXingZh on 19-3-21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceImplTest {

    @Autowired
    ArticleService articleService;

    @Test
    public void test() {
        List<Article> articles = articleService.getAllBlog();
        System.out.println(articles);
    }
}