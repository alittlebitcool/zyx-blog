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
@Table(name = "comment")
public class Comment {
    private String id;
    private String username;
    private String email;
    private Date createTime;
    private String comment;
}
