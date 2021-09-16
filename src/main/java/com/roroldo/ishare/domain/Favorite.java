package com.roroldo.ishare.domain;

/**
 * 收藏表实体对象
 * @author 落霞不孤
 */
public class Favorite {
    private int uid;
    private int csId;
    private Course course;
    private User user;

    public Favorite() {
    }

    public Favorite(Course course, User user) {
        this.course = course;
        this.user = user;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getCsId() {
        return csId;
    }

    public void setCsId(int csId) {
        this.csId = csId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
