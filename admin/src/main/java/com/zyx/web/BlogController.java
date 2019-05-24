package com.zyx.web;

import com.zyx.feign.ArticleFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author YuXingZh
 * @Date ：Created in 17:37 2019/5/24
 * @Description：delete or add operation
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    ArticleFeign articleFeign;

    @PostMapping(value = "/deleteArticle")
    public void deleteArticle(int articleId) {
        articleFeign.deleteArticle(articleId);
    }

    @RequestMapping(value = "/addArticle", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String addArticle(@RequestBody  Map<String, Object> map) {
        articleFeign.addArticle(map);
        return "/index";
    }

}
