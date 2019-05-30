package com.zyx.web;

import com.zyx.feign.ElasticsearchFeign;
import com.zyx.service.ArticleService;
import com.zyx.service.TagService;
import com.zyx.util.Transformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by YuXingZh on 19-3-20
 */
@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    ArticleService articleService;

    @Autowired
    ElasticsearchFeign elasticsearchFeign;

    @Autowired
    TagService tagService;

    @GetMapping("/showAll")
    public String listEsblogs(
            @RequestParam(value = "order", required = false, defaultValue = "new") String order,
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(value = "async", required = false) boolean async,
            @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            Model model) {
        model.addAttribute("blogList", articleService.getAllBlog());
        model.addAttribute("hotList", articleService.selectHotArticle());
        model.addAttribute("tags", tagService.selectAllTags());
        return "/index";
    }

    @GetMapping("/keyWordsSearch")
    public String keyWordsSearch(
            @RequestParam(value = "keyWords", required = false, defaultValue = "new") String keyWords,
            @RequestParam(value = "order", required = false, defaultValue = "new") String order,
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(value = "async", required = false) boolean async,
            @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            Model model) throws IOException, ParseException {
        model.addAttribute("blogList",
                Transformation.jsontoarticle(elasticsearchFeign.keyWordsSearch(keyWords)));
        model.addAttribute("hotList", articleService.selectHotArticle());
        model.addAttribute("tags", tagService.selectAllTags());
        return "/index";
    }

    @GetMapping("/tagSearch")
    public String tagSearch(@RequestParam(value = "tag", required =
            false) String tag, Model model) throws IOException, ParseException {
        model.addAttribute("blogList", articleService.tagSearch(tag));
        model.addAttribute("hotList", articleService.selectHotArticle());
        model.addAttribute("tags", tagService.selectAllTags());
        return "/index";
    }
}
