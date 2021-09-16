package com.roroldo.ishare.web.servlet;

import com.roroldo.ishare.domain.*;
import com.roroldo.ishare.service.CategoryService;
import com.roroldo.ishare.service.CourseService;
import com.roroldo.ishare.service.FavoriteService;
import com.roroldo.ishare.service.impl.CategoryServiceImpl;
import com.roroldo.ishare.service.impl.CourseServiceImpl;
import com.roroldo.ishare.service.impl.FavoriteServiceImpl;
import com.roroldo.ishare.util.MyThreadPool;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 课程Servlet
 * @author 落霞不孤
 */
@WebServlet("/course")
public class CourseServlet extends BaseServlet {
    private CourseService courseService = new CourseServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();

    /**
     * 分页查询课程（bug 模糊查询时直接传递% 搜索信息不回显）
     * @param request request
     * @param response response
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 用户的搜索信息
        String searchMsg = "";
        // 接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("rows");
        // csName课程名称
        String csName = request.getParameter("csName");
        // 分页标志
        if (csName != null && csName.length() > 0) {
            // tomcat7没有解决Get请求乱码问题，我们翻页查询时请求时get方式的，只能自己判断对中文转码
            // 我们将来时部署到tomcat8中的可以不管get请求中文乱码，到时候把这段注释就行
            if ("GET".equals(request.getMethod())) {
                csName = new String(csName.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            }
            // 用户通过关键词搜索
            System.out.println(csName);
            searchMsg = csName;
        }

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
        // 调用service查询pageBean对象
        PageBean<Course> pb = courseService.pageQuery(currentPage, pageSize, csName, 0);
        // 将搜索信息、pb转发到search_list页面
        request.setAttribute("search_msg", searchMsg);
        request.setAttribute("pb", pb);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/search_list.jsp");
        requestDispatcher.forward(request, response);
    }

    public void queryByCategoryId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("rows");
        String cidStr = request.getParameter("cid");
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
        int cid = Integer.parseInt(cidStr);
        Category category = categoryService.findOne(cid);
        // 调用service查询pageBean对象
        PageBean<Course> pb = courseService.pageQueryByCid(cid, currentPage, pageSize);
        // 将搜索信息、pb转发到search_list页面
        request.setAttribute("pb", pb);
        request.setAttribute("category", category);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/course_list.jsp");
        requestDispatcher.forward(request, response);
    }


    /**
     * 根据csId查找课程
     * @param request request
     * @param response response
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String csIdStr = request.getParameter("csId");
        int csId = Integer.parseInt(csIdStr);
        Course course = courseService.findOne(csId);
        request.setAttribute("course", course);
        request.getRequestDispatcher("/course_detail.jsp").forward(request, response);
    }

     /**
     * 查找课程分类是cid的所有课程
     * @param request request
     * @param response response
     */
    public void findAllByCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cidStr = request.getParameter("cid");
        int cid = Integer.parseInt(cidStr);
        List<Course> list = courseService.findAllByCid(cid);
        // 结果序列化为json返回
        writeValue(list ,response);
    }

    /**
     * 用户上传课程的分页查询
     * 这里其实可以不另外写方法，统一在pageQuery处理，转发根据uid来就行
     * @param request request
     * @param response response
     */
    public void pageQueryForUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        // 获取登录用户
        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getUid();
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
        PageBean<Course> pb = courseService.pageQuery(currentPage, pageSize, null, uid);
        // 将搜索信息、pb转发到my_upload.jsp页面
        request.setAttribute("pb", pb);
        request.getRequestDispatcher("/my_upload.jsp").forward(request, response);
    }

    /**
     * 查找需要更新的课程信息并转发到更新页面
     * @param request request
     * @param response response
     * @throws ServletException e
     * @throws IOException e
     */
    public void findOneForUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String csIdStr = request.getParameter("csId");
        int csId = Integer.parseInt(csIdStr);
        Course course = courseService.findOne(csId);
        // 把课程分类信息注入到category中
        Category category = categoryService.findOne(course.getCid());
        course.setCategory(category);
        request.setAttribute("course", course);
        request.getRequestDispatcher("/update_course.jsp").forward(request, response);
    }

    /**
     * 更新课程信息
     * @param request request
     * @param response response
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String csId = request.getParameter("csId");
        String cid = request.getParameter("cid");
        String uri = request.getParameter("uri");
        String code = request.getParameter("code");
        courseService.update(Integer.parseInt(csId), Integer.parseInt(cid), -1, uri, code);
        response.sendRedirect(request.getContextPath() + "/course?method=pageQueryForUpload");
    }

    /**
     * 删除用户上传单个课程信息
     * @param request request
     * @param response response
     */
    public void deleteOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String csId = request.getParameter("csId");
        // 获取登录用户
        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getUid();
        courseService.delete(Integer.parseInt(csId), uid);
        response.sendRedirect(request.getContextPath() + "/course?method=pageQueryForUpload");
    }

    /**
     * 批量删除用户上传课程
     * @param request request
     * @param response response
     * @throws IOException e
     */
    public void delSelected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取所有被删除收藏课程的csId
        String[] csIds = request.getParameterValues("csId");
        // 获取登录用户uid
        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getUid();
        // 调用courseService删除选中课程
        courseService.deleteSelected(csIds, uid);
        // 这里注意，一定要重定向到my_upload的分页查询方法中，不然数据没有刷新
        response.sendRedirect(request.getContextPath() + "/course?method=pageQueryForUpload");
    }

    /**
     * 用户上传课程
     * @param request request
     * @param response response
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 保存课程信息
        List<Object> values = new ArrayList<>();
        String savePath = "/root/tomcat/ishare/upload";
        // String savePath = "src/main/webapp/upload";
        // 保存图片文件路径
        StringBuilder csImgPath = new StringBuilder("upload/");
        // 创建工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 创建文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 解决上传文件名的中文乱码
        upload.setHeaderEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        // 判断表单上传类型
        if (!ServletFileUpload.isMultipartContent(request)) {
            return;
        }
        // 使用ServletFileUpload解析器解析上传数据
        // 解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
        List<FileItem> list = upload.parseRequest(request);
        for (FileItem item: list) {
            // 如果item封装的是普通输入项的数据
            if (item.isFormField()) {
                // 获取普通输入项数据
                String name = item.getFieldName();
                String value = item.getString();
                if ("cid".equals(name)) {
                    // 分类编号字符串 转数字
                    int cid = Integer.parseInt(value);
                    values.add(cid);
                } else {
                    // 重新编码
                    value = new String(value.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                    values.add(value);
                }
            } else {
                // item封装的是上传文件
                // 获取文件名
                /*String fileName = item.getName();
                if (fileName != null && !"".equals(fileName.trim())) {
                    // 得到上传文件的扩展名
                    String fileExtName = fileName.substring(fileName.lastIndexOf("."));
                    fileName = UUID.randomUUID().toString() + fileExtName;
                    csImgPath.append(fileName);
                    item.write(new File(savePath, fileName));
                }*/
            }
        }
        // values的内容依次为csName、csAuthor、csIntroduce、cid、uri、code
        // 现在把图片路径和用户id注入
        csImgPath.append("default.jpg");
        values.add(csImgPath.toString());
        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getUid();
        values.add(uid);
        // 调用courseService方法添加课程
        courseService.add(values);
        // 这里注意，一定要重定向my_upload的分页查询方法中，不然数据没有刷新
        response.sendRedirect(request.getContextPath() + "/course?method=pageQueryForUpload");
    }
}
