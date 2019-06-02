package com.zyx.web;

import com.zyx.entity.Article;
import com.zyx.service.ArticleService;
import com.zyx.service.TagService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @Author YuXingZh
 * @Date ：Created in 19:29 2019/5/23
 * @Description：FeignController
 */
@RestController
@RequestMapping("/feign")
public class FeignController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @ResponseBody
    @RequestMapping("/showAll")
    public List<Article> showAll() {
        return articleService.getAllBlog();
    }

    @ResponseBody
    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    public int addArticleByMap(@RequestBody Map<String, Object> map) throws ParseException {
        String tags = (String) map.get("tags");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Article article = new Article();
        article.setContent((String) map.get("content"));
        article.setCreateTime(simpleDateFormat.parse((String) map.get("createTime")));
        article.setIntroduction((String) map.get("introduction"));
        article.setModifyTime(simpleDateFormat.parse((String) map.get("createTime")));
        article.setTitle((String) map.get("title"));
        articleService.addArticleId(article);
        if (!StringUtils.isEmpty(tags)) {
            tagService.addTags(tags, article.getId());
        }
        return article.getId();
    }

    @RequestMapping(value = "/deleteArticle",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public void deleteArticle(@RequestParam("articleId") int articleId) {
        articleService.deleteArticle(articleId);
    }

    @ResponseBody
    @RequestMapping(value = "/updateArticle", method = RequestMethod.POST)
    public int updateArticle(@RequestBody Map<String, Object> map) throws ParseException {
        articleService.updateArticle(map);
        return Integer.valueOf((String)map.get("id"));
    }

    @ResponseBody
    @RequestMapping(value = "/getSpecialArticle",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public Article getSpecialArticle(@RequestParam("articleId") int articleId) throws ParseException {
        return articleService.getSpecialArticle(articleId);
    }
}
