package com.roroldo.ishare.service;

import com.roroldo.ishare.domain.Course;
import com.roroldo.ishare.domain.PageBean;

import java.util.List;

/**
 * CourseService
 * @author 落霞不孤
 */
public interface CourseService {
    /**
     * 含有csName关键字的课程信息或者uid用户上传的课程信息
     * @param currentPage 当前页码
     * @param pageSize 每页条数
     * @param csName 课程名字
     * @param uid uid
     * @return 含有csName关键字的课程信息的集合
     */
    public PageBean<Course> pageQuery(int currentPage, int pageSize, String csName, int uid);

    /**
     * 通过课程csId查找课程
     * @param csId 课程csId
     * @return 课程
     */
    public Course findOne(int csId);

    /**
     * 查找课程分类是cid的所有课程
     * @param cid 课程分类cid
     * @return 课程分类是cid的所有课程
     */
    public List<Course> findAllByCid(int cid);

    /**
     * 更新课程信息
     * @param csId 课程csId
     * @param cid cid
     * @param count 收藏次数
     * @param uri uri
     * @param code code
     */
    void update(int csId, int cid, int count ,String uri, String code);

    /**
     * 删除用户uid上传课程
     * @param csId 课程id
     * @param uid 用户id
     */
    void delete(int csId, int uid);

    /**
     * 批量删除用户上传课程
     * @param csIds 课程的ids
     * @param uid 用户id
     */
    void deleteSelected(String[] csIds, int uid);

    /**
     * 用户上传课程
     * @param values 课程信息
     */
    void add(List<Object> values);

    /**
     * 根据 cid 分页查询课程
     * @param cid cid
     * @param currentPage currentPage
     * @param pageSize  pageSize
     * @return  PageBean<Course>
     */
    PageBean<Course> pageQueryByCid(int cid, int currentPage, int pageSize);
}
