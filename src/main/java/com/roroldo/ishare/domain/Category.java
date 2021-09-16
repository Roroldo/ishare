package com.roroldo.ishare.domain;

import java.io.Serializable;

/**
 * 分类实体类
 * @author 落霞不孤
 */
public class Category implements Serializable {
    /**
     * 分类id
     */
    private int cid;
    /**
     * 分类名称
     */
    private String cname;

    public Category() {
    }

    public Category(int id, String cname) {
        this.cid = id;
        this.cname = cname;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + cid +
                ", name='" + cname + '\'' +
                '}';
    }
}
