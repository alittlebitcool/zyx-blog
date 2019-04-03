package com.zyx.feign;

import com.zyx.entity.Article;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by YuXingZh on 19-4-4
 */
@Component
public class FeignFallBack implements FeignService {

    @Override
    public List<Article> getAllBlog(){
        return null;
    }
}
