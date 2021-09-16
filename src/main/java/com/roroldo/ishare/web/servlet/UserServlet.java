package com.roroldo.ishare.web.servlet;

import com.roroldo.ishare.domain.ResultInfo;
import com.roroldo.ishare.domain.User;
import com.roroldo.ishare.service.UserService;
import com.roroldo.ishare.service.impl.UserServiceImpl;
import com.roroldo.ishare.util.CookieUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 用户控制器
 * @author 落霞不孤
 */
@WebServlet("/user")
public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();

    /**
     * 注册功能
     * @param request request
     * @param response response
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取数据
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        // 验证码校验
        String checkCode = request.getParameter("verifycode");
        // 从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        // 为了验证码只能使用一次
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(checkCode)) {
            // 验证码错误
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("注册失败，验证码错误！");
            info.setData(user);
            // 存储并转发错误信息到注册页面
            request.setAttribute("info", info);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        // 调用service完成注册
        boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo();
        if (flag) {
            // 注册成功
            info.setFlag(true);
            // 重定向到注册成功页面
            response.sendRedirect(request.getContextPath() + "/register_ok.jsp");
        } else {
            // 注册失败 存储并转发错误信息到注册页面
            info.setFlag(false);
            info.setErrorMsg("注册失败，用户名已存在！");
            info.setData(user);
            request.setAttribute("info", info);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    /**
     * 用户激活功能
     * @param request request
     * @param response response
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取激活码
        String code = request.getParameter("code");
        if (code != null) {
            // 调用service完成激活
            boolean flag = service.active(code);
            String msg = null;
            if (flag) {
                msg = "激活成功，请<a href='login.jsp'>登录</a>";
            } else {
                msg = "激活失败，请联系<a href='mailto:roroldo@gmail.com'>管理员</a>！";
            }
            response.getWriter().write(msg);
        }
    }

    /**
     * 登录功能
     * @param request request
     * @param response response
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 获取用户名和密码
        Map<String, String[]> map = request.getParameterMap();
        // 封装user对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        // 验证码校验
        String checkCode = request.getParameter("verifycode");
        // 从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        // 为了验证码只能使用一次
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(checkCode)) {
            // 验证码错误
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setData(user);
            info.setErrorMsg("登录失败，验证码错误！");
            // 存储并转发错误信息到登录页面
            request.setAttribute("info", info);
            try {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
            return;
        }
        // 调用service查询
        User loginUser = service.login(user);
        ResultInfo info = new ResultInfo();
        // 不管用户有木有成功登录 先把数据存起来便于回显
        info.setData(user);
        // 是否存在该用户
        if (loginUser == null) {
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误！");
        }
        // 存在没激活
        if (loginUser != null && !"Y".equals(loginUser.getStatus())) {
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请到您的邮箱处激活您的账号！");
        }
        // 存在并且已经激活
        if (loginUser != null && "Y".equals(loginUser.getStatus())) {
            // 判断用户是否开启了自动登录
            String autoLogin = request.getParameter("autoLogin");
            // 开启了自动登录
            if ("true".equals(autoLogin)) {
                // 先对cookie进行编码然后在发送出去，原因是tomcat7并不支持中文的存储
                String userMsg = user.getUsername() + "@" + user.getPassword();
                userMsg = URLEncoder.encode(userMsg, "UTF-8");
                System.out.println(userMsg);
                Cookie autoLoginCookie = new Cookie("autoLoginCookie", userMsg);
                // 设置cookie的发送路径和存活时间，将cookie信息发送给客户端
                autoLoginCookie.setPath("/");
                autoLoginCookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(autoLoginCookie);
            } else {
                // 删除cookie
                Cookie autoLoginCookie = new Cookie("autoLoginCookie", "");
                // 设置cookie的发送路径和存活时间，将cookie信息发送给客户端
                autoLoginCookie.setPath("/");
                autoLoginCookie.setMaxAge(0);
                response.addCookie(autoLoginCookie);
            }
            // 把用户存入session中
            request.getSession().setAttribute("user", loginUser);
            info.setFlag(true);
            // 登录成功
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        // 登录失败场景
        request.setAttribute("info", info);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    /**
     * 查找登录用户
     * @param request request
     * @param response response
     * @throws IOException e
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 从session获取的登录用户
        Object user = request.getSession().getAttribute("user");
        writeValue(user, response);
    }

    /**
     * 用户退出
     * @param request request
     * @param response response
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 销毁session和自动登录的cookie信息
        request.getSession().invalidate();
        // 删除cookie
        Cookie autoLoginCookie = new Cookie("autoLoginCookie", "");
        // 设置cookie的发送路径和存活时间，将cookie信息发送给客户端
        autoLoginCookie.setPath("/");
        autoLoginCookie.setMaxAge(0);
        response.addCookie(autoLoginCookie);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
