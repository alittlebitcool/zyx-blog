package com.zyx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;

/**
 * Created by YuXingZh on 19-3-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="article")
public class Article {
    private int id;
    private String title;
    private String introduction;
    private String content;
    private Date createTime;
    private Date modifyTime;
    private int likes;
}
