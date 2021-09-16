package com.roroldo.ishare.service;

import com.roroldo.ishare.domain.Favorite;
import com.roroldo.ishare.domain.PageBean;

/**
 * 收藏表业务
 * @author 落霞不孤
 */
public interface FavoriteService {
    /**
     * 判断uid的用户是否受篡改了csId的课程
     * @param csId 课程Id
     * @param uid 用户id
     * @return true代表已收藏
     */
    boolean isFavorite(String csId, int uid);

    /**
     * 添加收藏课程
     * @param csId csId
     * @param uid uid
     */
    void add(String csId, int uid);

    /**
     * 分页查询收藏课程信息
     * @param currentPage 当前页码
     * @param pageSize 每页条数
     * @param uid uid
     * @return 分页查询收藏课程信息
     */
    PageBean<Favorite> pageQuery(int currentPage, int pageSize, int uid);

    /**
     * 批量删除收藏课程
     * @param uid 用户uid
     * @param csIds 所有收藏课程的csId
     */
    void deleteSelectedFavorite(int uid, String[] csIds);

    /**
     * 查询课程被收藏数量
     * @param csId 课程csId
     * @return 课程被收藏次数
     */
     int findTotalCountByCsId(String csId);
}
