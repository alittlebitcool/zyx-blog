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
@Table(name = "tag")
public class Tag {
    private String id;
    private String name;
    private Date createTime;
    private Date modifyTime;
}
