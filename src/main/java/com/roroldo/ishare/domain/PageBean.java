package com.roroldo.ishare.domain;

import java.util.List;

/**
 * 分页对象
 * @author 落霞不孤
 */
public class PageBean<T> {
    /**
     * 总记录数
     */
    private int totalCount;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 当前页
     */
    private int currentPage;
    /**
     * 每页数据条数
     */
    private int pageSize;
    /**
     * 查询的总记录
     */
    private List<T> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
