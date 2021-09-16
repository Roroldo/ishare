package com.roroldo.ishare.web.servlet;

import com.roroldo.ishare.domain.Category;
import com.roroldo.ishare.service.CategoryService;
import com.roroldo.ishare.service.impl.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 分类servlet
 * @author 落霞不孤
 */
@WebServlet("/category")
public class CategoryServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryServiceImpl();

    /**
     * 查询所课程分类信息
     * @param request request
     * @param response response
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 调用service查询所有
        List<Category> cs = categoryService.findAll();
        // 序列化json返回
        writeValue(cs, response);
    }
}
