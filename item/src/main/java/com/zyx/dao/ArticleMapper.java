package com.zyx.dao;

import com.zyx.entity.Article;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by YuXingZh on 19-3-19
 */
public interface ArticleMapper extends Mapper<Article>,
        SelectByIdListMapper<Article, Integer> {

    /**
     * Select hot article in database
     */
    @Select("SELECT * FROM article ORDER BY likes LIMIT 4")
    List<Article> selectHotArticle();
}
