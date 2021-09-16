package com.roroldo.ishare.domain;


/**
 * 用户实体类
 * @author 落霞不孤
 */
public class User {
    /**
     * 用户id
     */
    private int uid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户激活状态
     */
    private String status;
    /**
     * 用户激活码，唯一的
     */
    private String code;

    public User() {
    }

    public User(int uid, String username, String password, String email, String status, String code) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
        this.code = code;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
