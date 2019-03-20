package com.zyx.service.Impl;

import com.zyx.dao.ArticleMapper;
import com.zyx.entity.Article;
import com.zyx.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YuXingZh on 19-3-20
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    /**
     * 获取所有的博客
     */
    @Override
    public List<Article> getAllBlog() {
        return articleMapper.selectAll();
    }
}
