package com.zyx.service.Impl;

import com.zyx.dao.ArticleMapper;
import com.zyx.dao.CommentArticleMapper;
import com.zyx.dao.CommentMapper;
import com.zyx.entity.Article;
import com.zyx.entity.Comment;
import com.zyx.entity.vo.CommentArticle;
import com.zyx.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collector;
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

    public List<Integer> getAllTags(int articleId) {
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
    public List<Comment> getAllComment(int articleId) {
        return commentArticleMapper.selectByIdList(getAllTags(articleId));
    }
}
