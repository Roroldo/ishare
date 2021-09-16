package com.roroldo.ishare.web.filter;

import com.roroldo.ishare.domain.User;
import com.roroldo.ishare.service.UserService;
import com.roroldo.ishare.service.impl.UserServiceImpl;
import com.roroldo.ishare.util.CookieUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 登陆过滤器，实现自动登录
 * @author 落霞不孤
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 接口强转
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String servletPath = request.getServletPath();
        System.out.println("servletPath = " + servletPath);
        // 如果是登录页面 /user?method=login, 直接放行
        if (servletPath.startsWith("/user") || servletPath.startsWith("/login.jsp")) {
            String method = request.getParameter("method");
            if ("login".equals(method)) {
                chain.doFilter(request, response);
                return;
            }
        }
        // 获取登录用户信息
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser != null) {
            // 用户已经登录，直接放行，不需要自动登录
            chain.doFilter(request, response);
            return;
        }

        // 获取自动登录的cookie信息
        Cookie userCookie = CookieUtils.getCookie(request.getCookies(), "autoLoginCookie");
        // 判断自动登陆的cookie是否存在，不存在则不需要自动登录
        if (userCookie == null) {
            chain.doFilter(request, response);
            return;
        }
        // 通过用户的cookie中记录信息，查询用户
        // 获取用户信息，先解码
        String msg = userCookie.getValue();
        msg = URLDecoder.decode(msg, "UTF-8");
        System.out.println(msg);
        String[] userMsg = msg.split("@");
        String username = userMsg[0];
        String password = userMsg[1];
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        try {
            UserService userService = new UserServiceImpl();
            loginUser = userService.login(user);
            // 没有查询到，直接放行
            if (loginUser == null) {
                chain.doFilter(request, response);
                return;
            }
            // 自动登录 放行
            request.getSession().setAttribute("user", loginUser);
            chain.doFilter(request, response);
        } catch (Exception e) {
            System.out.println("自动登录异常，请自动忽略~");
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
