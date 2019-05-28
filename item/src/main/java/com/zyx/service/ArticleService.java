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
    List<Article> getAllBlog();

    /**
     * get special article
     */
    Article getSpecial(int articleId);

    /**
     * add an article
     */
    void addArticleId(Article article);

    /**
     * delete an article
     */
    void deleteArticle(int articleId);

    /**
     * Select three hottest article
     */
    List<Article> selectHotArticle();

    /**
     * 根据title进行推荐
     */
    List<Article> recommendArticle(String title);
}
