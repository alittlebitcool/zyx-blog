package com.zyx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by YuXingZh on 19-3-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    private String id;
    @Column(name = "user_name")
    private String username;
    private String email;
    @Column(name = "create_time")
    private Date createTime;
    private String comment;
}
