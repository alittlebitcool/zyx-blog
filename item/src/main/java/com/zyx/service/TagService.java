package com.zyx.service;

import com.zyx.entity.vo.CommentArticle;
import com.zyx.entity.vo.TagArticle;

import java.util.List;

/**
 * @Author YuXingZh
 * @Date ：Created in 12:13 2019/5/13
 * @Description：
 */
public interface TagService {

    /**
     * get all the tags from the tag table
     * @return
     */
    public List<CommentArticle> getAllTags(int articleId);
}
