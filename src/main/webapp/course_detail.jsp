<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>课程详情</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <!-- Bootstrap -->
    <link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="css/common.css" rel="stylesheet">
    <link type="text/css" href="css/course_detail.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-migrate-1.0.0.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/include.js"></script>
</head>
<body>
    <div class="wrap">
        <div id="header"></div>
        <div id="main">
            <div class="container">
                    <div class="row">
                        <!--课程信息展示-->
                            <div class="row">
                                <div class="col-md-4">
                                    <img src="${course.csImg}">
                                </div>
                                <div class="container col-md-8">
                                    <div class="row detail_row">
                                        课程名称：${course.csName}
                                    </div>
                                    <div class="row detail_row">
                                        主讲老师：${course.csAuthor}
                                    </div>
                                    <div class="row detail_row">
                                        课程简介：<span>${course.csIntroduce}</span>
                                    </div>
                                    <!--收藏按钮-->
                                    <div class="row collect">
                                        <a class="btn" onclick="addFavorite();" id="favorite"><i class="glyphicon glyphicon-heart-empty disabled"></i>点击收藏</a>
                                        <span id="countCollect" style="font-size: 12px;color: cadetblue;vertical-align: -10px">已被收藏 <font color="#ff4500">${course.count}</font> 次数</span>
                                    </div>
                                </div>
                            </div>
                    </div>

                    <div class="course_tips"><h3>课程须知</h3></div>
                    <hr>
                    <div>
                        <p>1.只有收藏课程后，才能在我的收藏处查看课程的网盘资源；</p>
                        <p>2.请不要私下与课程的老师联系，以免本站遭遇不测；</p>
                        <p>3.禁止把课程二次出售盈利，一旦发现，终身封号；</p>
                        <p>4.如果课程资料缺失或者收藏以后网盘链接失效，请联系管理员解决；</p>
                        <p>5.课程虽好，但不要贪多，选择最适合自己的就行。</p>
                    </div>
            </div>
        </div>
    </div>

    <div id="footer"></div>

    <script>
        // 用户收藏课程
        function addFavorite() {
            // 判断用户是否登录
            $.get("user?method=findOne", {}, function (user) {
                if (user) {
                    // 用户已经登录
                    // 发送收藏请求
                    $.post("favorite?method=addFavorite", {csId:${course.csId}}, function () {
                        alert("收藏成功，请到我的收藏处查看课程网盘资源~");
                        // 刷新页面
                        location.reload();
                    });
                    // 设置按钮的样式
                    $("#favorite").addClass("already", "disabled");
                    $("#favorite").attr("disabled", "disabled");
                    // 删除按钮点击事件
                    $("#favorite").removeAttr("onclick");
                } else {
                    alert("您尚未登录，请登录再点击收藏！");
                    location.href = "login.jsp";
                }

            });
        }

        $(function () {
            /*判断用户有没有收藏该课程*/
            $.post("favorite?method=isFavorite", {csId:${course.csId}}, function (flag) {
                if (flag) {
                    // 用户已经收藏了
                    $("#favorite").addClass("already", "disabled");
                    $("#favorite").attr("disabled", "disabled");
                    // 删除按钮点击事件
                    $("#favorite").removeAttr("onclick");
                } else {
                    // 没有收藏
                }

            });

        })
    </script>
</body>
</html>