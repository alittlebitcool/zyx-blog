package com.zyx.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;
import java.util.List;

/**
 * @Author YuXingZh
 * @Date ：Created in 14:23 2019/5/17
 * @Description：elasticsearch entity class
 */
@Document(indexName = "blog",type = "goods",shards = 1,replicas = 0,
        refreshInterval = "-1")
@Data
public class Article {
    @Id
    private Integer id;
    private String title;
    private String introduction;
    private String content;
    private Date createTime;
    private Date modifyTime;
    private Integer likes;

}
