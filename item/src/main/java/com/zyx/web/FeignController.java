package com.zyx.web;

import com.zyx.api.ArticleApi;
import com.zyx.entity.Article;
import com.zyx.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @ResponseBody
    @RequestMapping("/showAll")
    public List<Article> showAll() {
        return articleService.getAllBlog();
    }
}
