package com.roroldo.ishare.dao.impl;

import com.roroldo.ishare.dao.CommentDao;
import com.roroldo.ishare.domain.Comment;
import com.roroldo.ishare.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * CommentDaoImpl
 * @author 落霞不孤
 */
public class CommentDaoImpl implements CommentDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int totalCount(String content) {
        String sql = "select count(*) from tab_comment where answerCommentId = 0";
        if (content == null || content.length() == 0) {
            return template.queryForObject(sql, Integer.class);
        } else {
            StringBuilder sb = new StringBuilder(sql);
            sb.append(" and content like ?");
            List<Object> values = new ArrayList<>();
            values.add("%" + content + "%");
            return template.queryForObject(sb.toString(), Integer.class, values.toArray());
        }
    }

    @Override
    public List<Comment> queryCommentForList(int start, int pageSize) {
        String sql = "select * from tab_comment where answerCommentId = 0 order by count desc";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();
        params.add(start);
        params.add(pageSize);
        sb.append(" limit ?,?");
        sql = sb.toString();
        System.out.println(sb.toString());
        return template.query(sql, new BeanPropertyRowMapper<>(Comment.class), params.toArray());
    }

    @Override
    public List<Comment> queryCommentForList(String content, int start, int pageSize) {
        String sql = "select * from tab_comment where answerCommentId = 0 and content like ? order by count desc";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();
        params.add("%" + content + "%");
        params.add(start);
        params.add(pageSize);
        sb.append(" limit ?,?");
        sql = sb.toString();
        System.out.println(sb.toString());
        return template.query(sql, new BeanPropertyRowMapper<>(Comment.class), params.toArray());
    }

    @Override
    public List<Comment> queryCommentForListByAnswerCommentId(int commentId) {
        String sql = "select * from tab_comment where answerCommentId = ?";
        return template.query(sql, new BeanPropertyRowMapper<>(Comment.class), commentId);
    }

    @Override
    public void addCount(int commentId) {
        String sql = "update tab_comment set count = count + 1 where commentId = ?";
        template.update(sql, commentId);
    }

    @Override
    public void deleteByAnswerCommentId(int answerCommentId) {
        String sql = "delete from tab_comment where answerCommentId = ?";
        template.update(sql, answerCommentId);
    }

    @Override
    public void deleteByCommentId(int commentId) {
        String sql = "delete from tab_comment where answerCommentId = 0 and commentId = ?";
        template.update(sql, commentId);

    }

    @Override
    public void addComment(List<Object> values) {
        String sql = "insert into tab_comment(content, sendDateStr, count, uid, answerCommentId) values(?,?,?,?,?)";
        template.update(sql, values.toArray());
    }

    @Override
    public Comment findCommentByCid(int cid) {
        String sql = "select * from tab_comment where answerCommentId = 0 and commentId = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Comment.class), cid);
    }
}
