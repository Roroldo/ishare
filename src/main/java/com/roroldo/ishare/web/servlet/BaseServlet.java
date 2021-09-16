package com.roroldo.ishare.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 基础的servlet，负责方法的转发
 * @author 落霞不孤
 */
class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // System.out.println("baseServlet 的方法被执行了~");
        // 完成方法的转发
        // 获取方法名字参数 http://localhost/ishare/user?method=login
        String methodName = req.getParameter("method");
        System.out.println("methodName = " + methodName);
        // 获取方法对象 谁调用service方法， this就代表谁
        // System.out.println(this);
        try {
            // 这里可以暴力反射，但是最好不要
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将传入的对象obj序列化为json，并写回客户端
     * @param obj 对象
     * @param response response
     */
    protected void writeValue(Object obj, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), obj);
    }

    /**
     * 将传入的对象obj序列化为json，并返回其字符串
     * @param obj 对象
     * @return 对象obj序列化为json的字符串
     */
    protected String writeValueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
