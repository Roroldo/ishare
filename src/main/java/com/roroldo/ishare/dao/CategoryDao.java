package com.roroldo.ishare.dao;

import com.roroldo.ishare.domain.Category;

import java.util.List;

/**
 * 操作 Category的dao
 * @author 落霞不孤
 */
public interface CategoryDao {
    /**
     * 查找所有的分类信息
     * @return 所有的分类信息
     */
    public List<Category> findAll();

    /**
     * 通过分类cid查找课程分类
     * @param cid 分类id
     * @return 课程分类
     */
    public Category findByCid(int cid);

    /**
     * 通过课程分类名称查找课程分类cid
     * @param cname 课程分类
     * @return 课程分类
     */
    public Category findByCname(String cname);
}
