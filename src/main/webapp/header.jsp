<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>导航条</title>
    <!-- Bootstrap -->
    <link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-migrate-1.0.0.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script type="text/javascript" src="js/bootstrap.min.js"></script>

    <script>
        $(function () {
            // 查询分类信息
            $.post("category?method=findAll", {}, function (data) {
                // json数据格式：[{"cid":1,"cname":"计算机基础"},{"cid":2,"cname":"编程语言"},{"cid":3,"cname":"就业体系"}]
                var lis = "";
                // 遍历数组，拼接字符串
                for (var i = 0; i < data.length; i++) {
                    var li = '<li><a href="${pageContext.request.contextPath}/course?method=queryByCategoryId&cid='+ data[i].cid +'">'+ data[i].cname +'</a></li>';
                    lis += li;
                }
                // 将lis字符串设置到ul的html内容中
                $(".category").html(lis);
            });

            // 有输入搜索信息才提交搜索表单
            $("#searchForm").submit(function () {
                // 获取搜索框内容
                var csName = $(".csName").val();
                if (csName == null || csName.length === 0 || csName.trim().length == 0) {
                    alert("您输入的搜索信息为空，请重新搜索！");
                    return false;
                }
                return true;
            });
        });
    </script>
</head>
<body>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand active" href="index.jsp">首页</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav category">
                    <!--课程分类栏目-->
                </ul>
                <form class="navbar-form navbar-left form-inline" action="${pageContext.request.contextPath}/course?method=pageQuery" method="post" id="searchForm">
                    <div class="form-group">
                        <input type="text" class="form-control csName" placeholder="请输入需要查找的课程信息" name="csName">
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${user != null}">
                        <li style="margin-top: 15px">
                            <font color="yellow">${user.username}，欢迎你~</font>
                        </li>
                        <li><a href="${pageContext.request.contextPath}/favorite?method=pageQueryForCollect"><span class="glyphicon glyphicon-star"></span> 我的收藏</a></li>
                        <!--用户只有登录成功后才能看到这两个按钮-->
                        <li><a href="${pageContext.request.contextPath}/course?method=pageQueryForUpload"><span class="glyphicon glyphicon-heart"></span> 我的贡献</a></li>
                        <%--留言板--%>
                        <li><li><a href="${pageContext.request.contextPath}/comment?method=queryForList"><span class="glyphicon glyphicon-check"></span> 留言板</a></li>
                        <li><a href="${pageContext.request.contextPath}/user?method=exit"><span class="glyphicon glyphicon-remove-sign"></span> 退出</a></li>
                    </c:if>
                    <c:if test="${user == null}">
                        <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span > 登录</a></li>
                        <li><a href="register.jsp"><span class="glyphicon glyphicon-ok-sign"></span > 注册</a></li>
                    </c:if>
                    <li><a href="rule.jsp"><span class="glyphicon glyphicon-file"></span> 关于本站</a></li>
                </ul>
            </div>
        </div>
    </nav>
</body>
</html>