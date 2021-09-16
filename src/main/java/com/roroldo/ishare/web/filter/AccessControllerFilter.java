package com.roroldo.ishare.web.filter;

import com.roroldo.ishare.domain.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限控制器
 * @author 落霞不孤
 */
@WebFilter(value = "/*")
public class AccessControllerFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 判断用户有木有登录，登录就放行
        // 接口强转
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // 获取登录用户信息
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser != null) {
            // 用户已经登录，直接放行，不需要拦截
            chain.doFilter(request, response);
        } else {
            // 没有登陆时，如果直接访问页面资源，需要登录
            String servletPath = request.getServletPath();
            if ("/update_course.jsp".equals(servletPath) || "/upload_course.jsp".equals(servletPath)
                || "/my_favorite.jsp".equals(servletPath) || "/my_upload.jsp".equals(servletPath)
                || "/comment_list.jsp".equals(servletPath)) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
            // 访问方法时,这里是我的贡献
            String methodName = request.getParameter("method");
            if ("pageQueryForUpload".equals(methodName)) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
            // 拦截我的收藏页面
            if ("pageQueryForCollect".equals(methodName)) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }
}

