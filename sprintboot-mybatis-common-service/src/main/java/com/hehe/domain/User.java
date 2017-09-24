package com.hehe.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_user") //最好指定表名
public class User {

    @Id //务必指定主键 不加默认全字段联合主键影响性能
    @Column(name = "user_id") //符合驼峰映射可不写Column
    private String userId;
    @Column(name = "username") //符合驼峰映射可不写Column
    private String username;
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
