package com.roroldo.ishare.dao;

import com.roroldo.ishare.domain.Course;
import com.roroldo.ishare.domain.PageBean;

import java.util.List;

/**
 * CourseDao
 * @author 落霞不孤
 */
public interface CourseDao {
    /**
     * 根据课程分类cid或课程名字csName或uid用户上传的课程数
     * @param cid 课程分类
     * @param csName 课程名字
     * @param uid 用户uid
     * @return 课程分类为cid或课程名为csName或uid用户上传的课程课程数
     */
    public int findTotalCount(int cid, String csName, int uid);

    /**
     * 分页查询课程分类为cid的课程
     * @param start 开始索引
     * @param pageSize 每页记录
     * @param csName 课程名字
     * @param uid uid
     * @return 课程名为csName的课程集合
     */
    public List<Course> findByPage(int start, int pageSize, String csName, int uid);

    /**
     * 通过课程csId查找课程
     * @param csId 课程csId
     * @return 课程
     */
    public Course findBycsId(int csId);

    /**
     * 通过课程分类cid查找所有的课程
     * @param cid 课程分类
     * @return 所有的课程
     */
    public List<Course> findByCid(int cid);

    /**
     * 更新课程信息
     * @param csId 课程csId
     * @param cid cid
     * @param count 收藏次数
     * @param uri uri
     * @param code code
     */
    void update(int csId, int cid, int count, String uri, String code);

    /**
     * 删除用户uid上传课程
     * @param csId 课程id
     * @param uid 用户id
     */
    void delete(int csId, int uid);

    /**
     * 添加课程
     * @param values 课程信息
     */
    void add(List<Object> values);

    /**
     * 根据 cid 查询课程
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Course> queryByCid(int cid, int currentPage, int pageSize);

    /**
     * 根据 cid 查找课程
     * @param cid
     */
    int findTotalCount(int cid);
}
