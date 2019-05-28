package com.zyx.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zyx.entity.Article;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author YuXingZh
 * @Date ：Created in 19:37 2019/5/28
 * @Description：
 */
public class Transformation {
    public static List<Article> jsontoarticle(List<Map<String, Object>> keyWordsSearch) throws ParseException {
        List<Article> res = new ArrayList<>();

        for (Map<String, Object> map : keyWordsSearch) {
            Article article = new Article();
            article.setTitle((String) map.get("title"));
            article.setContent((String) map.get("content"));
            article.setId(Integer.parseInt((String) map.get("id")));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            article.setCreateTime(sdf.parse((String) map.get("createTime")));
            article.setIntroduction((String) map.get("introduction"));
            res.add(article);
        }
        return res;
    }

}
