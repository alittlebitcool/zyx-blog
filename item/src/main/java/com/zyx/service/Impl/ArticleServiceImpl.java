package com.zyx.service.Impl;

import com.hankcs.hanlp.suggest.Suggester;
import com.zyx.dao.ArticleMapper;
import com.zyx.dao.TagArticleMapper;
import com.zyx.dao.TagMapper;
import com.zyx.entity.Article;
import com.zyx.entity.Tag;
import com.zyx.entity.vo.TagArticle;
import com.zyx.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by YuXingZh on 19-3-20
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    TagMapper tagMapper;

    @Autowired
    TagArticleMapper tagArticleMapper;

    /**
     * 获取所有的博客
     */
    @Override
    public List<Article> getAllBlog() {
        return articleMapper.selectAll();
    }

    /**
     * get special article
     */
    @Override
    public Article getSpecial(int articleId) {
        return articleMapper.selectByPrimaryKey(articleId);
    }

    /**
     * add an article
     *
     * @param article
     */
    @Override
    public void addArticleId(Article article) {
        articleMapper.insertSelective(article);
    }

    /**
     * delete an article
     *
     * @param articleId
     */
    @Override
    public void deleteArticle(int articleId) {
        articleMapper.deleteByPrimaryKey(articleId);
    }

    /**
     * Select three hottest article
     */
    @Override
    public List<Article> selectHotArticle() {
        return articleMapper.selectHotArticle();
    }

    /**
     * 根据title进行推荐
     *
     * @param title
     * @return
     */
    @Override
    public List<Article> recommendArticle(String title) {
        Suggester suggester = new Suggester();
        List<Article> articles = articleMapper.selectAll();
        List<String> introductions =
                articles.stream().map(i -> i.getIntroduction()).distinct().collect(Collectors.toList());
        for (String introduction : introductions) {
            suggester.addSentence(introduction);
        }

        List<String> suggest = suggester.suggest(title, 6);
        List<Article> res = new ArrayList<>();
        int random1 = (int) (Math.random() * 5) + 1;
        ;
        int random2 = (random1 + 1) % 5;
        for (Article article : articles) {
            if (article.getIntroduction().equals(suggest.get(random1)) && res.size() < 2) {
                res.add(article);
            }
            if (article.getIntroduction().equals(suggest.get(random2)) && res.size() < 2) {
                res.add(article);
            }
        }
        return res;
    }

    /**
     * update datase article
     *
     * @param map
     */
    @Override
    public void updateArticle(Map<String, Object> map) throws ParseException {
        Article article = new Article();
        article.setId(Integer.valueOf((String) map.get("id")));
        article.setTitle((String) map.get("title"));
        article.setContent((String) map.get("content"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        article.setModifyTime(sdf.parse((String) map.get("createTime")));
        article.setCreateTime(sdf.parse((String) map.get("createTime")));
        article.setIntroduction((String) map.get("introduction"));
        articleMapper.updateByPrimaryKeySelective(article);
    }

    /**
     * get article
     *
     * @param articleId
     */
    @Override
    public Article getSpecialArticle(int articleId) {
        return articleMapper.selectByPrimaryKey(articleId);
    }

    /**
     * get tag's correspond article
     *
     * @param
     * @return
     */
    @Override
    public List<Article> tagSearch(String name) {
        List<Integer> tagIds = tagMapper.tagSearch(name).stream().map(Tag::getId).collect(Collectors.toList());
        List<TagArticle> select = new ArrayList<>();
        for (int tagId : tagIds) {
            TagArticle tagArticle = new TagArticle();
            tagArticle.setTagId(tagId);
            select.add(tagArticleMapper.selectOne(tagArticle));
        }
        return articleMapper.selectByIdList(select.stream().map(TagArticle::getArticleId).collect(Collectors.toList()));
    }
}
