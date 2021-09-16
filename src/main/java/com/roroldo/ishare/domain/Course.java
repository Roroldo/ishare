package com.roroldo.ishare.domain;

import java.io.Serializable;

/**
 * 课程实体类
 * @author 落霞不孤
 */
public class Course {
    /**
     * 课程id
     */
    private int csId;
    /**
     * 课程名字
     */
    private String csName;
    /**
     * 课程老师
     */
    private String csAuthor;
    /**
     * 课程介绍
     */
    private String csIntroduce;
    /**
     * 课程图片
     */
    private String csImg;
    /**
     * 课程收藏次数
     */
    private int count;
    /**
     * 课程网盘链接
     */
    private String uri;
    /**
     * 课程提取码
     */
    private String code;
    /**
     * 课程分类id
     */
    private int cid;
    /**
     * 上传课程的用户id
     */
    private int uid;
    /**
     * 所属分类
     */
    private Category category;
    /**
     * 上传的用户
     */
    private User user;

    public Course() {
    }

    public Course(int csId, String csName, String csAuthor, String csIntroduce, String csImg, int count, String uri, String code, int cid, int uid) {
        this.csId = csId;
        this.csName = csName;
        this.csAuthor = csAuthor;
        this.csIntroduce = csIntroduce;
        this.csImg = csImg;
        this.count = count;
        this.uri = uri;
        this.code = code;
        this.cid = cid;
        this.uid = uid;
    }

    public int getCsId() {
        return csId;
    }

    public void setCsId(int csId) {
        this.csId = csId;
    }

    public String getCsName() {
        return csName;
    }

    public void setCsName(String csName) {
        this.csName = csName;
    }

    public String getCsAuthor() {
        return csAuthor;
    }

    public void setCsAuthor(String csAuthor) {
        this.csAuthor = csAuthor;
    }

    public String getCsIntroduce() {
        return csIntroduce;
    }

    public void setCsIntroduce(String csIntroduce) {
        this.csIntroduce = csIntroduce;
    }

    public String getCsImg() {
        return csImg;
    }

    public void setCsImg(String csImg) {
        this.csImg = csImg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
