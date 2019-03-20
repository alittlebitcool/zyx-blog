package com.zyx.service;

import com.zyx.entity.Article;

import java.util.List;

/**
 * Created by YuXingZh on 19-3-20
 */
public interface ArticleService {
    /**
     * 获取所有的博客
     */
    public List<Article> getAllBlog();
}
