package com.zyx.dao;

import com.zyx.entity.Article;
import com.zyx.entity.Comment;
import com.zyx.entity.vo.CommentArticle;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author YuXingZh
 * @Date ：Created in 13:08 2019/5/13
 * @Description：
 */
public interface CommentArticleMapper extends Mapper<CommentArticle>,
        SelectByIdListMapper<Comment, Integer> {
}
