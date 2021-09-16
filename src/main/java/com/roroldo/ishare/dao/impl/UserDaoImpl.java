package com.roroldo.ishare.dao.impl;

import com.roroldo.ishare.dao.UserDao;
import com.roroldo.ishare.domain.User;
import com.roroldo.ishare.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 操作用户的实现类
 * @author 落霞不孤
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            String sql = "select * from tab_user where username = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void save(User user) {
        String sql = "insert into tab_user(username, password, email, status, code) values(?,?,?,?,?)";
        template.update(sql, user.getUsername(), user.getPassword(),
                user.getEmail(), user.getStatus(),
                user.getCode());
    }

    @Override
    public User findByCode(String code) {
        User user = null;
        try {
            String sql = "select * from tab_user where code = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateStatus(User user) {
        String sql = "update tab_user set status = 'Y' where uid = ?";
        template.update(sql, user.getUid());
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            String sql = "select * from tab_user where username = ? and password = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        } catch (Exception e) {

        }
        return user;
    }

    @Override
    public User findByUid(int uid) {
        User user = null;
        try {
            String sql = "select * from tab_user where uid = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), uid);
        } catch (Exception e) {

        }
        return user;
    }
}
