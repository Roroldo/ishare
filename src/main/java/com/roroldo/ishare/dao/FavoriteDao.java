package com.roroldo.ishare.dao;

import com.roroldo.ishare.domain.Favorite;

import java.util.List;

/**
 * 收藏表操作接口
 * @author 落霞不孤
 */
public interface FavoriteDao {
    /**
     * 查找uid用户收藏的csId收藏条目
     * @param csId csId
     * @param uid uid
     * @return 收藏条目
     */
    Favorite findByCsIdAndUid(int csId, int uid);

    /**
     * 查询课程收藏次数
     * @param csId csId
     * @return 课程收藏次数
     */
    int findCountByCsId(int csId);

    /**
     * uid用户收藏课程csID
     * @param csId csId
     * @param uid uid
     */
    void add(int csId, int uid);

    /**
     * 查看uid用户收藏的课程数量
     * @param uid uid
     * @return uid用户收藏的课程数量
     */
    int findTotalCount(int uid);

    /**
     * 分页查询用户uid的收藏课程
     * @param start start
     * @param pageSize 每页大小
     * @param uid uid
     * @return 分页查询用户uid的收藏课程
     */
    List<Favorite> findByPage(int start, int pageSize, int uid);

    /**
     * 删除指定收藏课程
     * @param uid 用户uid
     * @param csId
     */
    void delete(int uid, int csId);
}
