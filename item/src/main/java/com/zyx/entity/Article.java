package com.zyx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * Created by YuXingZh on 19-3-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="article")
public class Article {
    private String id;
    private String title;
    private String introduction;
    private String html;
    private String markdown;
    private String createTime;
    private String modifyTime;
    private String likes;
}
