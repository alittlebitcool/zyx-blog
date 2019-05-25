package com.zyx.web;

import com.zyx.feign.ArticleFeign;
import com.zyx.feign.ElasticsearchFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.text.ParseException;
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
    @Autowired
    ElasticsearchFegin elasticsearchFegin;

    @PostMapping(value = "/deleteArticle")
    public void deleteArticle(int articleId) throws IOException {
        articleFeign.deleteArticle(articleId);
        elasticsearchFegin.deleteDocument(String.valueOf(articleId));
    }

    @RequestMapping(value = "/addArticle", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String addArticle(@RequestBody Map<String, Object> map) throws IOException, ParseException {
        int articleId = articleFeign.addArticle(map);
        map.put("id", articleId);
        elasticsearchFegin.addDocument(map);
        return "/index";
    }

}
