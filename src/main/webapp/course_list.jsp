<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored ="false" %>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>搜索结果</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <!-- Bootstrap -->
    <link type="text/css" href="css/common.css" rel="stylesheet">
    <link type="text/css" href="css/search_list.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-migrate-1.0.0.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/include.js"></script>
</head>
<body>

<div class="wrap">
    <%--两种引入方式的区别？仅仅是速度问题吗？--%>
    <%@ include file="header.jsp"%>
    <div id="main">
        <div class="container">
            <img src="img/search.png">
            <span class="search_title">
                <b>ishare > ${category.cname} </b>
            </span>
            <div class="row">
                <div class="course_msg_title">课程信息</div>
            </div>
            <%--没有找到用户搜索课程时--%>
            <c:if test="${pb.totalCount == 0}">
                <div class="row">
                    <div class="col-md-12">
                        <center>不好意思 >_<, &nbsp;您查找的课程暂时还没有人分享哦~</center>
                    </div>
                </div>
            </c:if>

            <c:if test="${pb.list != null}">
                <%--搜索结果信息展示--%>
                <c:forEach items="${pb.list}" var="course">
                    <div class="row course_msg" style="margin-top: 30px">
                        <div class="col-lg-4">
                            <img src="${course.csImg}" width="300px" height="100px">
                        </div>
                        <div class="container col-lg-8">
                            <div class="row course_title">
                                <b>${course.csName}</b>
                            </div>
                            <div class="row course_detail">
                                <p>
                                        ${course.csIntroduce}
                                </p>
                            </div>
                            <div class="row find_course">
                                <a href="
                            ${pageContext.request.contextPath}/course?method=findOne&csId=${course.csId}"><b>查看详情</b></a>
                            </div>
                        </div>
                        <br>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
        <!--分页效果-->
    <div style="font-size: 16px;margin-left: 25px;">
            | 共${pb.totalCount}条记录，共${pb.totalPage}页
    </div>
    <c:if test="${pb.totalCount != 0}">
        <div style="margin-left:25px">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <%--第一页上一页效果--%>
                    <c:if test="${pb.currentPage == 1}">
                        <li class="disabled">
                    </c:if>

                    <%--中间页码上一页效果 --%>
                    <c:if test="${pb.currentPage != 1}">
                        <li>
                    </c:if>
                            <a href="${pageContext.request.contextPath}/course?method=queryByCategoryId&cid=${category.cid}&currentPage=${pb.currentPage - 1}&rows=4">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                         </li>
                    <%--页码展示--%>
                    <%--这里为了实现分码分页效果，就给用户体验感好一点
                    不要一下子把页码全部展示出来
                    其余页面分页查询改动也类似--%>
                    <%--定义begin和end变量--%>
                    <c:set var="begin" scope="request"></c:set>
                    <c:set var="end" scope="request"></c:set>
                    <%--每个页面显示五个页码数--%>
                    <c:if test="${pb.totalPage < 5}">
                        <%--不够五个页码数时--%>
                        <c:set var="begin" value="1" scope="request"></c:set>
                        <c:set var="end" value="${pb.totalPage}" scope="request"></c:set>
                    </c:if>
                    <c:if test="${pb.totalPage >= 5}">
                        <%-- 超过五个页码数时--%>
                        <%--实现前2后2效果
                              1.如果前面不够2个，则后面补齐5个
                              2.如果后面面不够2个，则前面补齐5个
                         --%>
                        <c:set var="begin" value="${pb.currentPage - 2}" scope="request"></c:set>
                        <c:set var="end" value="${pb.currentPage + 2}" scope="request"></c:set>
                        <%--前边不够2个，后面补齐--%>
                        <c:if test="${begin < 1}">
                            <c:set var="begin" value="1" scope="request"></c:set>
                            <c:set var="end" value="${begin + 4}" scope="request"></c:set>
                        </c:if>
                        <%--后面不够2个，前面补齐--%>
                        <c:if test="${end > pb.totalPage}">
                            <c:set var="end" value="${pb.totalPage}" scope="request"></c:set>
                            <c:set var="begin" value="${end - 4}" scope="request"></c:set>
                        </c:if>
                    </c:if>
                    <c:forEach begin="${begin}" end="${end}" var="i">
                        <%--当前页码效果--%>
                        <c:if test="${pb.currentPage == i}">
                            <li class="active">
                                <a href="${pageContext.request.contextPath}/course?method=queryByCategoryId&cid=${category.cid}&currentPage=${i}&rows=4&csName=${search_msg}">
                                        ${i}
                                </a>
                            </li>
                        </c:if>
                        <%-- 不是当前页 --%>
                        <c:if test="${pb.currentPage != i}">
                            <li>
                                <a href="${pageContext.request.contextPath}/course?method=queryByCategoryId&cid=${category.cid}&currentPage=${i}&rows=4">
                                        ${i}
                                </a>
                            </li>
                        </c:if>
                    </c:forEach>

                    <%--下一页最后一页效果--%>
                    <c:if test="${pb.currentPage == pb.totalPage}">
                        <li class="disabled">
                    </c:if>

                    <c:if test="${pb.currentPage != pb.totalPage}">
                        <li>
                    </c:if>
                            <a href="${pageContext.request.contextPath}/course?method=queryByCategoryId&cid=${category.cid}&currentPage=${pb.currentPage + 1}&rows=4" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                </ul>
            </nav>
        </div>
    </c:if>
</div>
</body>
</html>