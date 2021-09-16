package com.roroldo.ishare.web.servlet;

import com.roroldo.ishare.domain.*;
import com.roroldo.ishare.service.*;
import com.roroldo.ishare.service.impl.*;
import com.roroldo.ishare.util.DateUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 留言 Comment
 * @author 落霞不孤
 */
@WebServlet("/comment")
public class CommentServlet extends BaseServlet {
    private CommentService commentService = new CommentServiceImpl();


    public void addComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String content = request.getParameter("content");
        System.out.println(content);
        User user = (User) request.getSession().getAttribute("user");
        String dateStr = DateUtil.getCurrentTimeDateStr();
        System.out.println(dateStr);
        int count = 0;
        int answerCommentId = 0;
        List<Object> values = new ArrayList<>();
        values.add(content);
        values.add(dateStr);
        values.add(count);
        values.add(user.getUid());
        values.add(answerCommentId);
        commentService.addComment(values);
        response.sendRedirect(request.getContextPath() + "/comment?method=queryForList");
    }

    public void queryForList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("rows");
        String content = request.getParameter("content");
        // 当前页码，不传递默认为1
        int currentPage = 1;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        }
        // 每页显示条数，默认4条一页
        int pageSize = 0;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 4;
        }
        PageBean<Comment> pb;
        if (content != null && content.trim().length() > 0) {
            if ("GET".equals(request.getMethod())) {
                content = new String(content.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            }
            request.setAttribute("searchContent", content);
            pb = commentService.queryCommentForList(content, currentPage, pageSize);
        } else {
            pb = commentService.queryCommentForList(currentPage, pageSize);
        }
        request.setAttribute("pb", pb);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/comment_list.jsp");
        requestDispatcher.forward(request, response);
    }


    public void addCommentCountByCid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String commentId = request.getParameter("cid");
        User user = (User) request.getSession().getAttribute("user");
        commentService.addCount(user.getUid(), Integer.parseInt(commentId));
        response.sendRedirect(request.getContextPath() + "/comment?method=queryForList");
    }

    public void deleteCommentByCid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String commentIdStr = request.getParameter("cid");
        int cid = Integer.parseInt(commentIdStr);
        commentService.deleteComment(cid, cid);
        response.sendRedirect(request.getContextPath() + "/comment?method=queryForList");
    }

    public void addAnswer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Object> values = new ArrayList<>();
        String cidStr = request.getParameter("cid");
        String content = request.getParameter("content");
        System.out.println(cidStr);
        int answerCommentId = Integer.parseInt(cidStr);
        User user = (User) request.getSession().getAttribute("user");
        String dateStr = DateUtil.getCurrentTimeDateStr();
        values.add(content);
        values.add(dateStr);
        values.add(0);
        values.add(user.getUid());
        values.add(answerCommentId);
        commentService.addAnswer(values, answerCommentId);
        response.sendRedirect(request.getContextPath() + "/comment?method=queryForList");
    }

    public void findAnswersByCid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cidStr = request.getParameter("cid");
        int cid = Integer.parseInt(cidStr);
        List<Comment> commentList = commentService.queryCommentForListByAnswerCommentId(cid);
        writeValue(commentList, response);
    }

    public void findCommentByCid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cidStr = request.getParameter("cid");
        int cid = Integer.parseInt(cidStr);
        Comment comment = commentService.findCommentByCid(cid);
        writeValue(comment, response);
    }
}
