package com.zyx.service.Impl;

import com.zyx.dao.CommentArticleMapper;
import com.zyx.dao.CommentMapper;
import com.zyx.entity.Comment;
import com.zyx.entity.vo.CommentArticle;
import com.zyx.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author YuXingZh
 * @Date ：Created in 15:40 2019/5/12
 * @Description：
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentArticleMapper commentArticleMapper;

    @Autowired
    CommentMapper commentMapper;

    public List<Integer> getAllIds(int articleId) {
        CommentArticle example = new CommentArticle();
        example.setArticleId(articleId);
        List<CommentArticle> list = commentArticleMapper.select(example);
        return list.stream().map(i -> i.getCommentId()).distinct().collect(Collectors.toList());
    }

    /**
     * get all the comments details from the comment table
     *
     * @param articleId
     * @return
     */
    @Override
    public List<Comment> getSpecialComment(int articleId) {
        return commentArticleMapper.selectByIdList(getAllIds(articleId));
    }

    /**
     * add comment in special article
     *
     * @param map
     * @return
     */
    @Override
    public int addComment(Map<String, Object> map) {
        Comment comment = new Comment();
        comment.setUsername((String) map.get("name"));
        comment.setComment((String) map.get("comment"));
        comment.setCreateTime(new Date());
        comment.setEmail((String) map.get("email"));
        commentMapper.insertSelective(comment);

        int id = Integer.valueOf((String) map.get("id"));

        CommentArticle commentArticle = new CommentArticle();
        commentArticle.setCommentId(comment.getId());
        commentArticle.setArticleId(id);
        commentArticle.setCreateTime(new Date());
        commentArticle.setModifyTime(new Date());
        commentArticleMapper.insertSelective(commentArticle);
        return id;
    }
}
