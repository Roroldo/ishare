package com.roroldo.ishare.service.impl;

import com.roroldo.ishare.dao.CourseDao;
import com.roroldo.ishare.dao.FavoriteDao;
import com.roroldo.ishare.dao.impl.CourseDaoImpl;
import com.roroldo.ishare.dao.impl.FavoriteDaoImpl;
import com.roroldo.ishare.domain.Course;
import com.roroldo.ishare.domain.PageBean;
import com.roroldo.ishare.service.CourseService;

import java.util.List;

/**
 *  CourseService实现类
 * @author 落霞不孤
 */
public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao = new CourseDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public PageBean<Course> pageQuery(int currentPage, int pageSize, String csName, int uid) {
        // 封装pageBean
        PageBean<Course> pb = new PageBean<>();
        // 设置每页显示的记录数
        pb.setPageSize(pageSize);
        // 设置总记录数
        int totalCount = courseDao.findTotalCount(0, csName, uid);
        pb.setTotalCount(totalCount);
        // 当前页显示的数据集合的开始下标
        // 设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount/pageSize : (totalCount/pageSize) + 1;
        if (currentPage <= 0) {
            currentPage = 1;
        }
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        // 设置当前页码
        pb.setCurrentPage(currentPage);
        pb.setTotalPage(totalPage);
        int start = (currentPage - 1) * pageSize;
        // 没有记录数，currentPage = 0，start就变为负数了
        if (start < 0) {
            start = 0;
        }
        // 设置当前页显示的数据集合
        List<Course> list = courseDao.findByPage(start, pageSize, csName, uid);
        pb.setList(list);
        return pb;
    }

    @Override
    public Course findOne(int csId) {
        Course course = courseDao.findBycsId(csId);
        int count = favoriteDao.findCountByCsId(csId);
        course.setCount(count);
        return course;
    }

    @Override
    public List<Course> findAllByCid(int cid) {
        return courseDao.findByCid(cid);
    }

    @Override
    public void update(int csId, int cid, int count, String uri, String code) {
        courseDao.update(csId, cid, count, uri, code);
    }

    @Override
    public void delete(int csId, int uid) {
        courseDao.delete(csId, uid);
    }

    @Override
    public void deleteSelected(String[] csIds, int uid) {
        if (csIds != null && csIds.length > 0) {
            for (String temp : csIds) {
                int csId = Integer.parseInt(temp);
                courseDao.delete(csId, uid);
            }
        }
    }

    @Override
    public void add(List<Object> values) {
        courseDao.add(values);
    }

    @Override
    public PageBean<Course> pageQueryByCid(int cid, int currentPage, int pageSize) {
        // 封装pageBean
        PageBean<Course> pb = new PageBean<>();
        // 设置每页显示的记录数
        pb.setPageSize(pageSize);
        // 设置总记录数
        int totalCount = courseDao.findTotalCount(cid);
        pb.setTotalCount(totalCount);
        // 当前页显示的数据集合的开始下标
        // 设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount/pageSize : (totalCount/pageSize) + 1;
        if (currentPage <= 0) {
            currentPage = 1;
        }
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        // 设置当前页码
        pb.setCurrentPage(currentPage);
        pb.setTotalPage(totalPage);
        int start = (currentPage - 1) * pageSize;
        // 没有记录数，currentPage = 0，start就变为负数了
        if (start < 0) {
            start = 0;
        }
        // 设置当前页显示的数据集合
        List<Course> list = courseDao.queryByCid(cid, start, pageSize);
        pb.setList(list);
        return pb;
    }
}
