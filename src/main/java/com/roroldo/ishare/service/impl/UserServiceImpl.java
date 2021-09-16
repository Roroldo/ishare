package com.roroldo.ishare.service.impl;

import com.roroldo.ishare.dao.UserDao;
import com.roroldo.ishare.dao.impl.UserDaoImpl;
import com.roroldo.ishare.domain.User;
import com.roroldo.ishare.service.UserService;
import com.roroldo.ishare.util.MailUtils;
import com.roroldo.ishare.util.MyThreadPool;
import com.roroldo.ishare.util.UuidUtil;

/**
 * 用户服务实现类
 * @author 落霞不孤
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean regist(User user) {
        // 根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        if (u != null) {
            // 已经存在用户名了，注册失败
            return false;
        }
        // 保存用户信息
        // 设置激活码，利用uuid工具类生成唯一字符串
        user.setCode(UuidUtil.getUuid());
        // 设置激活状态
        user.setStatus("N");
        userDao.save(user);
        MyThreadPool.execute(() -> {
            try {
                // 发送激活邮件
                // 邮件正文 把code作为参数发送出去，这样子我们就知道是谁激活了
                String content = "你好，恭喜你成为 ishare 社区的一员！<a href='http://8.131.69.234:8080/ishare/user?method=active&code="+ user.getCode() + "'>点击激活【ishare】。</a>";
                MailUtils.sendMail(user.getEmail(), content, "【ishare】激活");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return true;
    }

    @Override
    public boolean active(String code) {
        // 根据激活码查询用户对象
        User user = userDao.findByCode(code);
        if (user != null) {
            // 调用userDao修改用户激活状态
            userDao.updateStatus(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
