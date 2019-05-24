package com.zyx.web;

import com.zyx.dao.ArticleMapper;
import com.zyx.entity.Article;
import com.zyx.service.ArticleService;
import com.zyx.service.TagService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author YuXingZh
 * @Date ：Created in 19:29 2019/5/23
 * @Description：FeignController
 */
@Controller
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
    public void addArticleByMap(@RequestBody Map<String, Object> map) throws ParseException {
        String tags = (String) map.get("tags");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Article article = new Article();
        article.setContent((String) map.get("content"));
        article.setCreateTime(simpleDateFormat.parse((String) map.get("createTime")));
        article.setIntroduction((String) map.get("introduction"));
        article.setModifyTime(simpleDateFormat.parse((String) map.get("createTime")));
        article.setTitle((String) map.get("title"));
        articleService.addArticleId(article);
        tagService.addTags(tags, article.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/deleteArticle",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public void deleteArticle(int articleId) {
        articleService.deleteArticle(articleId);
    }

}
