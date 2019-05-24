package com.zyx.service;

import com.zyx.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * Created by YuXingZh on 19-3-20
 */
public interface ArticleService {

    /**
     * 获取所有的博客
     */
    public List<Article> getAllBlog();

    /**
     * get special article
     */
    public Article getSpecial(int articleId);

    /**
     * add an article
     */
    void addArticleId(Article article);

    /**
     * delete an article
     */
    void deleteArticle(int articleId);
}
