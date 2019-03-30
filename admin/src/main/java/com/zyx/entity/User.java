package com.zyx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by YuXingZh on 19-3-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class User {
    @Id
    private String id;
    private String userName;
    private String password;
    private String email;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
