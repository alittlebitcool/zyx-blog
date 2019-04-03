package com.zyx.feign;

import com.zyx.entity.Article;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.List;

/**
 * Created by YuXingZh on 19-4-4
 */
@FeignClient(value = "item",fallback = FeignFallBack.class)
public interface FeignService {
    //服务中方法的映射路径
    /**
     * 获取所有的博客
     */
    public List<Article> getAllBlog();
}
