package com.roroldo.ishare.dao;

import com.roroldo.ishare.domain.Comment;

import java.util.List;

/**
 * 留言表业务
 * @author 落霞不孤
 */
public interface CommentDao {
    /**
     * 留言数量
     * @param content
     */
    int totalCount(String content);

    /**
     * 加载留言板的留言
     */
    List<Comment>  queryCommentForList(int start, int pageSize);

    /**
     * 搜索留言板的留言
     */
    List<Comment>  queryCommentForList(String content, int start, int pageSize);

    /**
     * 加载某条留言的回答
     */
    List<Comment>  queryCommentForListByAnswerCommentId(int commentId);

    /**
     * 点赞数
     */
    void addCount(int commentId);

    /**
     * 删除留言(删除回应)
     */
    void deleteByAnswerCommentId(int answerCommentId);

    /**
     * 删除留言（用户的发出的留言）
     */
    void deleteByCommentId(int commentId);

    /**
     * 添加留言
     */
    void addComment(List<Object> values);

    Comment findCommentByCid(int cid);
}
