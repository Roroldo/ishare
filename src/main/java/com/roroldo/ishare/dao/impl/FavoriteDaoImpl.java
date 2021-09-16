package com.roroldo.ishare.dao.impl;

import com.roroldo.ishare.dao.FavoriteDao;
import com.roroldo.ishare.domain.Course;
import com.roroldo.ishare.domain.Favorite;
import com.roroldo.ishare.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 收藏表操作接口实现类
 * @author 落霞不孤
 */
public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite findByCsIdAndUid(int csId, int uid) {
        String sql = "select * from tab_favorite where csId = ? and uid = ?";
        Favorite favorite = null;
        try {
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<>(Favorite.class), csId, uid);
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return favorite;
    }

    @Override
    public int findCountByCsId(int csId) {
        String sql = "select count(*) from tab_favorite where csId = ?";
        return template.queryForObject(sql, Integer.class, csId);
    }

    @Override
    public void add(int csId, int uid) {
        String sql = "insert into tab_favorite values(?,?)";
        template.update(sql, uid, csId);
    }

    @Override
    public int findTotalCount(int uid) {
        String sql = "select count(*) from tab_favorite where uid = ?";
        return template.queryForObject(sql, Integer.class, uid);
    }

    @Override
    public List<Favorite> findByPage(int start, int pageSize, int uid) {
        String sql = "select * from tab_favorite where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();
        if (uid != 0) {
            sb.append("and uid = ?");
            params.add(uid);
        }
        sb.append(" limit ?, ?");
        sql = sb.toString();
        System.out.println("sql = " + sql);
        params.add(start);
        params.add(pageSize);
        return template.query(sql, new BeanPropertyRowMapper<>(Favorite.class), params.toArray());
    }

    @Override
    public void delete(int uid, int csId) {
        String sql = "delete from tab_favorite where uid = ? and csId = ?";
        template.update(sql, uid, csId);
    }
}
