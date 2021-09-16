package com.roroldo.ishare.domain;

/**
 * 留言
 * @author 落霞不孤
 */
public class Comment {
    /**
     * 留言 id
     */
    private Integer commentId;
    /**
     * 留言内容
     */
    private String content;
    /**
     * 留言日期
     */
    private String sendDateStr;
    /**
     * 点赞数
     */
    private int count;

    /**
     * 用户id
     */
    private int uid;

    /**
     * 留言用户
     */
    private User user;

    /**
     * 回应的留言 id
     */
    private int answerCommentId;

    public Comment() {
    }



    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendDateStr() {
        return sendDateStr;
    }

    public void setSendDateStr(String sendDateStr) {
        this.sendDateStr = sendDateStr;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAnswerCommentId() {
        return answerCommentId;
    }

    public void setAnswerCommentId(int answerCommentId) {
        this.answerCommentId = answerCommentId;
    }
}
