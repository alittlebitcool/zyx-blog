package com.zyx.feign;

import com.zyx.entity.Article;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @Author YuXingZh
 * @Date ：Created in 19:38 2019/5/23
 * @Description：
 */
@FeignClient(name = "item", url = "http://localhost:9400/feign")
public interface ArticleFeign {

    @RequestMapping(value = "/showAll", method = RequestMethod.GET,
            produces =
                    MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<Article> showAll();

    @RequestMapping(value = "/deleteArticle", method = RequestMethod.POST)
    void deleteArticle(@RequestParam(value = "articleId", required = true) int articleId);

    @RequestMapping(value = "/addArticle",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    void addArticle(@RequestBody Map<String, Object> map);
}
