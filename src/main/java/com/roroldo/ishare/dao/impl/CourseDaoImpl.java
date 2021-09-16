package com.roroldo.ishare.dao.impl;

import com.roroldo.ishare.dao.CourseDao;
import com.roroldo.ishare.domain.Course;
import com.roroldo.ishare.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * CourseDao实现类
 * @author 落霞不孤
 */
public class CourseDaoImpl implements CourseDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid, String csName, int uid) {
        // 定义 sql 模板
        String sql = "select count(*) from tab_course where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        // 条件参数集合
        List<Object> params = new ArrayList<>();
        if (cid != 0) {
            sb.append(" and cid = ?");
            params.add(cid);
        }
        if (csName != null && csName.length() > 0) {
            sb.append(" and csIntroduce like ?");
            params.add("%"+ csName +"%");
        }
        if (uid != 0) {
            sb.append(" and uid = ?");
            params.add(uid);
        }
        sql = sb.toString();
        System.out.println("sql = " + sql);
        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    @Override
    public List<Course> findByPage(int start, int pageSize, String csName, int uid) {
        String sql = "select * from tab_course where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();
        // 判断参数是否有值
        if (csName != null && csName.length() > 0) {
            sb.append(" and csIntroduce like ?");
            params.add("%"+ csName +"%");
        }
        if (uid != 0) {
            sb.append(" and uid = ?");
            params.add(uid);
        }
        sb.append(" limit ?,?");
        sql = sb.toString();
        System.out.println("sql = " + sql);
        params.add(start);
        params.add(pageSize);
        return template.query(sql, new BeanPropertyRowMapper<>(Course.class), params.toArray());
    }

    @Override
    public Course findBycsId(int csId) {
        String sql = "select * from tab_course where csId = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Course.class), csId);
    }

    @Override
    public List<Course> findByCid(int cid) {
        String sql = "select * from tab_course where cid = ?";
        return template.query(sql, new BeanPropertyRowMapper<>(Course.class), cid);
    }

    @Override
    public void update(int csId, int cid, int count, String uri, String code) {
        String sql = "update tab_course";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();
        // 修改只有两种可能，一是更新收藏次数，二是用户修改课程信息
        if (cid != 0 && (uri != null && uri.length() > 0) && (code != null && code.length() > 0)) {
            sb.append(" set cid = ?, uri = ?, code = ?");
            params.add(cid);
            params.add(uri);
            params.add(code);
        }
        if (count != -1) {
            sb.append(" set count = ?");
            params.add(count);
        }
        sb.append(" where csId = ?");
        sql = sb.toString();
        System.out.println(sql);
        params.add(csId);
        template.update(sql, params.toArray());
    }

    @Override
    public void delete(int csId, int uid) {
        String sql = "delete from tab_course where csId = ? and uid = ?";
        template.update(sql, csId, uid);
    }

    @Override
    public void add(List<Object> values) {
        String sql = "insert into tab_course(csName, csAuthor, csIntroduce, cid, uri, code, csImg, uid) values(?,?,?,?,?,?,?,?)";
        template.update(sql, values.toArray());
    }

    @Override
    public List<Course> queryByCid(int cid, int currentPage, int pageSize) {
        String sql = "select * from tab_course where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();
        // 判断参数是否有值
        if (cid != 0) {
            sb.append(" and cid = ?");
            params.add(cid);
        }
        sb.append(" limit ?,?");
        sql = sb.toString();
        System.out.println("sql = " + sql);
        params.add(currentPage);
        params.add(pageSize);
        return template.query(sql, new BeanPropertyRowMapper<>(Course.class), params.toArray());
    }

    @Override
    public int findTotalCount(int cid) {
        return findTotalCount(cid, null, 0);
    }
}
