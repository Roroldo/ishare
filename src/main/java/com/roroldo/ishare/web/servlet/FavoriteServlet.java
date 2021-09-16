package com.roroldo.ishare.web.servlet;

import com.roroldo.ishare.domain.Course;
import com.roroldo.ishare.domain.Favorite;
import com.roroldo.ishare.domain.PageBean;
import com.roroldo.ishare.domain.User;
import com.roroldo.ishare.service.CourseService;
import com.roroldo.ishare.service.FavoriteService;
import com.roroldo.ishare.service.impl.CourseServiceImpl;
import com.roroldo.ishare.service.impl.FavoriteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * favorite servlet
 * @author 落霞不孤
 */
@WebServlet("/favorite")
public class FavoriteServlet extends BaseServlet {
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    /**
     * 判断登录用户是否收藏过此课程
     * @param request request
     * @param response response
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取课程csId
        String csId = request.getParameter("csId");
        // 获取登录用户
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null) {
            // 用户没有登陆
            uid = 0;
        } else {
            uid = user.getUid();
        }
        // 调用favoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(csId, uid);
        writeValue(flag, response);
    }

    /**
     * 添加收藏课程
     * @param request request
     * @param response response
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) {
        // 获取课程csId
        String csId = request.getParameter("csId");
        // 获取登录用户
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null) {
            // 用户没有登陆
            return;
        } else {
            uid = user.getUid();
        }
        favoriteService.add(csId, uid);
    }

    /**
     * 分页查询我的收藏课程
     * @param request request
     * @param response response
     */
    public void pageQueryForCollect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        User user = (User) request.getSession().getAttribute("user");

        int currentPage = 1;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        }
        if (currentPage <= 0) {
            currentPage = 1;
        }
        int pageSize = 4;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        PageBean<Favorite> pb = favoriteService.pageQuery(currentPage, pageSize, user.getUid());
        request.setAttribute("pb", pb);
        request.getRequestDispatcher("/my_favorite.jsp").forward(request, response);
    }

    /**
     * 删除选中收藏课程
     * @param request request
     * @param response response
     */
    public void delSelected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取所有被删除收藏课程的csId
        String[] csIds = request.getParameterValues("csId");
        // 获取登录用户uid
        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getUid();
        // 调用favorite删除选中课程
        favoriteService.deleteSelectedFavorite(uid, csIds);
        // 这里注意，一定要重定向到favorite的分页查询方法中，不然数据没有刷新
        response.sendRedirect(request.getContextPath() + "/favorite?method=pageQueryForCollect");
    }
}
