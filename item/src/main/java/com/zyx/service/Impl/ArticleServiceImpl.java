package com.zyx.service.Impl;

import com.zyx.dao.ArticleMapper;
import com.zyx.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void getAllBlog() {
        articleMapper.selectAll();
    }
}
