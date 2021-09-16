package dao;


import com.roroldo.ishare.dao.CommentDao;
import com.roroldo.ishare.dao.impl.CommentDaoImpl;
import com.roroldo.ishare.domain.Comment;
import com.roroldo.ishare.util.DateUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class CommentDaoTest {
    private CommentDao commentDao = new CommentDaoImpl();

    @Test
    public void testAddCount() {
        commentDao.addCount(1);
    }

    @Test
    public void testQueryForList() {
        List<Comment> comments = commentDao.queryCommentForList(0, 4);
        for (int i = 0; i < comments.size(); i++) {
            System.out.println(comments);
        }
    }



    @Test
    public void testAdd() {
        List<Object> values = new ArrayList<>();
        values.add("你好 世界树");
        values.add(DateUtil.getCurrentTimeDateStr());
        values.add(2);
        values.add(7);
        values.add(0);
        commentDao.addComment(values);
    }

    @Test
    public void testDelete() {
        commentDao.deleteByAnswerCommentId(2);
        commentDao.deleteByCommentId(2);
    }
}
