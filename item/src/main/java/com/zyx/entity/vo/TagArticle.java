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
@Table(name="tag_article")
public class TagArticle {
    @Id
    private int id;
    @Column(name = "tag_id")
    private int tagId;
    @Column(name = "article_id")
    private int articleId;
    private Date createTime;
    private Date modifyTime;
}
