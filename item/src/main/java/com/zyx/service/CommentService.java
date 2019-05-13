package com.zyx.service;

import com.zyx.entity.Comment;
import com.zyx.entity.vo.CommentArticle;

import java.util.List;

/**
 * @Author YuXingZh
 * @Date ：Created in 15:40 2019/5/12
 * @Description：
 */
public interface CommentService {

//    /**
//     * get all the comments id linked to the article from the tag table
//     * @return
//     */
//    public List<CommentArticle> getAllTags(int articleId) ;

    /**
     * get all the comments details from the comment table
     * @return
     */
    public List<Comment> getAllComment(int articleId) ;
}
