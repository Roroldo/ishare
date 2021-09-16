package com.roroldo.ishare.dao;

import com.roroldo.ishare.domain.User;

/**
 * 操作用户表
 * @author 落霞不孤
 */
public interface UserDao {
    /**
     * 查找指定用户名的用户
     * @param username 用户名
     * @return 指定用户名的用户
     */
    public User findByUsername(String username);

    /**
     * 保存用户信息
     * @param user 用户
     */
    public void save(User user);

    /**
     * 根据激活码查找用户
     * @param code 激活码
     * @return 指定激活码的用户
     */
    public User findByCode(String code);

    /**
     * 更新用户的激活状态
     * @param user 用户
     */
    public void updateStatus(User user);

    /**
     * 根据用户名和密码查找用户
     * @return 用户
     */
    public User findByUsernameAndPassword(String username, String password);

    User findByUid(int uid);
}
