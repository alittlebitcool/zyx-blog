package com.zyx.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author YuXingZh
 * @Date ：Created in 12:22 2019/5/13
 * @Description：connection layer entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="comment_article")
public class CommentArticle {
    @Id
    private Integer id;
    @Column(name = "article_id")
    private Integer articleId;
    @Column(name = "comment_id")
    private Integer commentId;
    private Date createTime;
    private Date modifyTime;
}
