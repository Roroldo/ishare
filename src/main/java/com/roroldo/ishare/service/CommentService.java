package com.roroldo.ishare.service;

import com.roroldo.ishare.domain.Comment;
import com.roroldo.ishare.domain.PageBean;

import java.util.List;

/**
 * CommentService
 * @author 落霞不孤
 */
public interface CommentService {

    /**
     * 点赞数
     */
    void addCount(int uid, int commentId);

    /**
     * 删除留言
     */
    void deleteComment(int commentId, int answerCommentId);

    /**
     * 添加留言
     */
    void addComment(List<Object> values);

    PageBean<Comment> queryCommentForList(int currentPage, int pageSize);

    PageBean<Comment> queryCommentForList(String content, int currentPage, int pageSize);

    List<Comment> queryCommentForListByAnswerCommentId(int cid);

    Comment findCommentByCid(int cid);

    void addAnswer(List<Object> values, int cid);
}
