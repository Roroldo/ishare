package com.roroldo.ishare.service;

import com.roroldo.ishare.domain.User;

/**
 * 用户服务
 * @author 落霞不孤
 */
public interface UserService {
    /**
     * 用户注册
     * @param user 用户
     * @return true注册成功
     */
    boolean regist(User user);

    /**
     * 用户激活
     * @param code 激活码
     * @return true激活成功
     */
    boolean active(String code);

    /**
     * 用户登录
     * @param user 用户
     * @return 登录成功的用户
     */
    User login(User user);
}
