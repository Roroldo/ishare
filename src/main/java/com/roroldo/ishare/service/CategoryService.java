package com.roroldo.ishare.service;

import com.roroldo.ishare.domain.Category;

import java.util.List;

/**
 * 分类服务
 * @author 落霞不孤
 */
public interface CategoryService {
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
    public Category findOne(int cid);

    /**
     * 通过分类名称查找课程分类
     * @param cname 课程名称
     * @return 课程分类
     */
    public Category findOne(String cname);
}
