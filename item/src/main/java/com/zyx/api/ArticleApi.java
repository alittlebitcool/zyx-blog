package com.zyx.api;

import com.zyx.entity.Article;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by YuXingZh on 19-4-4
 */

@FeignClient(name = "item")
@RequestMapping("article")
public interface ArticleApi {
    /**a
     * 根据品牌id集合，查询品牌信息
     * @param ids
     * @return
     */
    @GetMapping("list")
    List<Article> queryBrandByIds(@RequestParam("ids") List<Long> ids);
}