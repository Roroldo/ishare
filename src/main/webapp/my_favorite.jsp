<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>我的收藏</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <!-- Bootstrap -->
    <link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="css/common.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-migrate-1.0.0.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <!--导入布局js，共享header和footer-->
    <script type="text/javascript" src="js/include.js"></script>
    <script>
        window.onload = function () {
            // 给删除选中按钮绑定单击事件
            document.getElementById("delSelected").onclick = function () {
                // 判断用户是否有选中的条目
                var cbs = document.getElementsByName("csId");
                var flag = false;
                for (var i = 0; i < cbs.length; i++) {
                    if (cbs[i].checked) {
                        // 有一个条目被选中了
                        flag = true;
                        break;
                    }
                }
                // 有条目被选中
                if (flag) {
                    if (confirm("您确认删除这些条目吗？")) {
                        // 表单提交
                        document.getElementById("form").submit();
                    }
                } else{
                    alert("您未选择任何的条目，请重新选择！");
                }
            };

            // 获取第一个cb
            document.getElementById("firstCb").onclick = function(){
                // 获取下边列表中所有的cb
                var cbs = document.getElementsByName("csId");
                // 遍历
                for (var i = 0; i < cbs.length; i++) {
                    // 设置这些cbs[i]的checked状态 = firstCb.checked
                    cbs[i].checked = this.checked;
                }
            }
        }
    </script>
</head>
<body>
    <div class="wrap">
        <div id="header"></div>
        <div id="main">
            <div class="container">
                <div style="color: orange; font-size:18px; float: left;">我的收藏</div>
                <div style="float: right; margin-bottom: 5px">
                    <a class="btn btn-danger" href="javascript:void(0);" id="delSelected">删除选中收藏课程</a>
                </div>

                <form action="${pageContext.request.contextPath}/favorite?method=delSelected" id="form" method="post">
                    <table border="1" class="table table-bordered table-hover">
                        <tr class="success">
                            <th><input type="checkbox" id="firstCb"></th>
                            <th>课程图片</th>
                            <th>课程名字</th>
                            <th>主讲老师</th>
                            <th>网盘链接</th>
                            <th>提取码</th>
                        </tr>
                        <c:if test="${pb.totalCount == 0}">
                            <!--没有收藏时-->
                            <tr>
                                <td colspan="7">
                                    <img style="margin-left: 125px" src="img/cartIsEmpty.png" width="869" height="400">
                                </td>
                            </tr>
                        </c:if>

                        <c:if test="${pb != null}">
                            <c:forEach items="${pb.list}" var="favorite" varStatus="s">
                                <tr>
                                    <th><input type="checkbox" name="csId" value="${favorite.course.csId}" id="csId"></th>
                                    <td><a href="${pageContext.request.contextPath}/course?method=findOne&csId=${favorite.course.csId}"><img src="${favorite.course.csImg}" width="100" height="50"></a></td>
                                    <td>${favorite.course.csName}</td>
                                    <td>${favorite.course.csAuthor}</td>
                                    <td><a href="${favorite.course.uri}" target="_blank">${favorite.course.uri}</a></td>
                                    <td>${favorite.course.code}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>
                </form>
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
                                <a href="${pageContext.request.contextPath}/favorite?method=pageQueryForCollect&currentPage=${pb.currentPage - 1}&rows=4&uid=${user.uid}">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        <%--页码展示--%>
                            
                        <c:forEach begin="1" end="${pb.totalPage}" var="i">
                            <%--当前页码效果--%>
                            <c:if test="${pb.currentPage == i}">
                                <li class="active">
                                    <a href="${pageContext.request.contextPath}/favorite?method=pageQueryForCollect&currentPage=${i}&rows=4&uid=${user.uid}">
                                            ${i}
                                    </a>
                                </li>
                            </c:if>
                            <%-- 不是当前页 --%>
                            <c:if test="${pb.currentPage != i}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/favorite?method=pageQueryForCollect&currentPage=${i}&rows=4&uid=${user.uid}">
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
                                <a href="${pageContext.request.contextPath}/favorite?method=pageQueryForCollect&currentPage=${pb.currentPage + 1}&rows=4&uid=${user.uid}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                    </ul>
                </nav>
            </div>
            <br><br><br>
        </c:if>
            <c:if test="${pb.totalCount == 0}">
                <br><br><br>
            </c:if>
    </div>
    </div>
    <div id="footer"></div>
</body>
</html>