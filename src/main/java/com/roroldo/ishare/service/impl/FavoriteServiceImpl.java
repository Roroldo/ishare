package com.roroldo.ishare.service.impl;

import com.roroldo.ishare.dao.CourseDao;
import com.roroldo.ishare.dao.FavoriteDao;
import com.roroldo.ishare.dao.impl.CourseDaoImpl;
import com.roroldo.ishare.dao.impl.FavoriteDaoImpl;
import com.roroldo.ishare.domain.Course;
import com.roroldo.ishare.domain.Favorite;
import com.roroldo.ishare.domain.PageBean;
import com.roroldo.ishare.service.FavoriteService;

import java.util.List;

/**
 * 收藏业务实现类
 * @author 落霞不孤
 */
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    private CourseDao courseDao = new CourseDaoImpl();

    @Override
    public boolean isFavorite(String csId, int uid) {
        Favorite favorite = favoriteDao.findByCsIdAndUid(Integer.parseInt(csId), uid);
        return favorite != null;
    }

    @Override
    public void add(String csId, int uid) {
        favoriteDao.add(Integer.parseInt(csId), uid);
        // 更新课程收藏信息
        int count = favoriteDao.findCountByCsId(Integer.parseInt(csId));
        courseDao.update(Integer.parseInt(csId), 0, count, null, null);
    }

    @Override
    public PageBean<Favorite> pageQuery(int currentPage, int pageSize, int uid) {
        PageBean<Favorite> pb = new PageBean<>();
        if (currentPage <= 0) {
            currentPage = 1;
        }
        int totalCount = favoriteDao.findTotalCount(uid);
        int totalPage = totalCount % pageSize == 0 ? totalCount/pageSize : (totalCount/pageSize) + 1;
        pb.setTotalPage(totalPage);
        if (currentPage >= totalPage) {
            currentPage = totalPage;
        }
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        pb.setTotalCount(totalCount);
        int start = (currentPage - 1) * pageSize;
        if (start < 0) {
            start = 0;
        }
        List<Favorite> list = favoriteDao.findByPage(start, pageSize, uid);
        pb.setList(list);
        // 把课程信息注入到favorite中
        for (Favorite favorite : list) {
            Course course = courseDao.findBycsId(favorite.getCsId());
            favorite.setCourse(course);
        }
        return pb;
    }

    @Override
    public void deleteSelectedFavorite(int uid, String[] csIds) {
        if (csIds != null && csIds.length > 0) {
            for (String csId : csIds) {
                favoriteDao.delete(uid, Integer.parseInt(csId));
                // 更新课程收藏信息
                int count = favoriteDao.findCountByCsId(Integer.parseInt(csId));
                courseDao.update(Integer.parseInt(csId), 0, count, null, null);
            }
        }
    }

    @Override
    public int findTotalCountByCsId(String csId) {
        return favoriteDao.findCountByCsId(Integer.parseInt(csId));
    }
}
