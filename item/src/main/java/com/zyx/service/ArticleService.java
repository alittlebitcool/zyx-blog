package com.zyx.service;

import com.zyx.entity.Article;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

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
     * recommend considering title
     */
    List<Article> recommendArticle(String title);

    /**
     * update datase article
     * @param map
     */
    void updateArticle(Map<String, Object> map) throws ParseException;

    /**
     * get article
     * @param articleId
     */
    Article getSpecialArticle(int articleId);

    /**
     * get tag's correspond article
     * @param tag
     * @return
     */
    List<Article> tagSearch(String tag);
}
