package com.zyx.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by YuXingZh on 19-3-19
 */
@Table(name="user")
@Data
public class User {
    @Id
    private String id;
    private String username;
    private String password;
}
