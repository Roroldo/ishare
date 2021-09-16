package com.roroldo.ishare.dao.impl;

import com.roroldo.ishare.dao.CategoryDao;
import com.roroldo.ishare.domain.Category;
import com.roroldo.ishare.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * CategoryDao实现类
 * @author 落霞不孤
 */
public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category";
        return template.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }

    @Override
    public Category findByCid(int cid) {
        String sql = "select * from tab_category where cid = ?";
        Category category = new Category();
        try {
            category = template.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), cid);
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return category;
    }

    @Override
    public Category findByCname(String cname) {
        String sql = "select * from tab_category where cname like ?";
        Category category = null;
        try {
            category = template.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), cname);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

}
