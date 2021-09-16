package com.roroldo.ishare.service.impl;

import com.roroldo.ishare.dao.CommentDao;
import com.roroldo.ishare.dao.UserDao;
import com.roroldo.ishare.dao.impl.CommentDaoImpl;
import com.roroldo.ishare.dao.impl.UserDaoImpl;
import com.roroldo.ishare.domain.Comment;
import com.roroldo.ishare.domain.PageBean;
import com.roroldo.ishare.domain.User;
import com.roroldo.ishare.service.CommentService;
import com.roroldo.ishare.util.JedisUtil;
import com.roroldo.ishare.util.MailUtils;
import com.roroldo.ishare.util.MyThreadPool;
import redis.clients.jedis.Jedis;


import java.util.List;

/**
 * CommentServiceImpl
 * @author 落霞不孤
 */
public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao = new CommentDaoImpl();
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void addCount(int uid, int commentId) {
        // 解决重复点赞问题
        Jedis jedis = JedisUtil.getJedis();
        String isAddCount = jedis.get(uid + ":isAddCount:" + commentId);
        if (isAddCount == null) {
            commentDao.addCount(commentId);
            jedis.set(uid + ":isAddCount:" + commentId, String.valueOf(1));
        }
        JedisUtil.close(jedis);
    }

    @Override
    public void deleteComment(int commentId, int answerCommentId) {
        commentDao.deleteByCommentId(commentId);
        commentDao.deleteByAnswerCommentId(answerCommentId);
    }

    @Override
    public void addComment(List<Object> values) {
        commentDao.addComment(values);
    }

    @Override
    public PageBean<Comment> queryCommentForList(int currentPage, int pageSize) {
        PageBean<Comment> pb = commentPageBean(null, currentPage, pageSize);
        int totalPage = pb.getTotalPage();
        if (currentPage >= totalPage) {
            currentPage = totalPage;
        }
        int start = countStart(currentPage, pageSize);
        List<Comment> commentList = commentDao.queryCommentForList(start, pageSize);
        for (Comment comment : commentList) {
            comment.setUser(userDao.findByUid(comment.getUid()));
        }
        pb.setList(commentList);
        return pb;
    }

    @Override
    public PageBean<Comment> queryCommentForList(String content, int currentPage, int pageSize) {
        PageBean<Comment> pb = commentPageBean(content, currentPage, pageSize);
        int totalPage = pb.getTotalPage();
        if (currentPage >= totalPage) {
            currentPage = totalPage;
        }
        int start = countStart(currentPage, pageSize);
        List<Comment> commentList = commentDao.queryCommentForList(content, start, pageSize);
        for (Comment comment : commentList) {
            comment.setUser(userDao.findByUid(comment.getUid()));
        }
        pb.setList(commentList);
        return pb;
    }

    @Override
    public List<Comment> queryCommentForListByAnswerCommentId(int commentId) {
        List<Comment> answerCommentId = commentDao.queryCommentForListByAnswerCommentId(commentId);
        for (Comment comment : answerCommentId) {
            comment.setUser(userDao.findByUid(comment.getUid()));
        }
        return answerCommentId;
    }

    @Override
    public Comment findCommentByCid(int cid) {
        Comment comment = commentDao.findCommentByCid(cid);
        comment.setUser(userDao.findByUid(comment.getUid()));
        return comment;
    }

    @Override
    public void addAnswer(List<Object> values, int cid) {
        String content = "你好，你有留言有新的回复，记得登录查看！";
        Comment comment = commentDao.findCommentByCid(cid);
        User revUser = userDao.findByUid(comment.getUid());
        MyThreadPool.execute(() -> {
            try {
                MailUtils.sendMail(revUser.getEmail(), content, "【ishare】新的留言提醒");
            } catch (Exception e) {
                // 邮箱地址无效
            }
        });
        commentDao.addComment(values);
    }


    private PageBean<Comment> commentPageBean(String content, int currentPage, int pageSize) {
        PageBean<Comment> pb = new PageBean<>();
        pb.setPageSize(pageSize);
        int totalCount;
        if (content != null && content.trim().length() > 0) {
            totalCount = commentDao.totalCount(content);
        } else {
            totalCount = commentDao.totalCount(null);
        }
        pb.setTotalCount(totalCount);
        int totalPage = totalCount % pageSize == 0 ? totalCount/pageSize : (totalCount/pageSize) + 1;
        if (currentPage <= 0) {
            currentPage = 1;
        }
        if (currentPage >= totalPage) {
            currentPage = totalPage;
        }
        pb.setCurrentPage(currentPage);
        pb.setTotalPage(totalPage);
        return pb;
    }

    private int countStart(int currentPage, int pageSize) {
        int start = (currentPage - 1) * pageSize;
        if (start < 0) {
            start = 0;
        }
        return start;
    }
}
