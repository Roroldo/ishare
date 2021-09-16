<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <title>注册成功</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="css/common.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-migrate-1.0.0.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <style>

    </style>
</head>
<body>
    <div class="wrap">
        <!--引入头部-->
        <jsp:include page="/header.jsp"></jsp:include>
        <div id="main">
            <div style="text-align:center; font-weight:bold;height:150px;padding-top:100px;">
                <h4 style="color:orangered;">恭喜，注册成功！请登录您的注册邮箱激活您的账号，激活后才能登录。</h4>
                <span id="time">5</span>秒之后，自动跳转登录页面。如果没有跳转，请点<a href="${pageContext.request.contextPath}/login.jsp">此处</a>立即跳转...
            </div>
        </div>
    </div>

    <script>
        // 自动跳转登陆页面
        var second = 5;
        var time = $("#time");
        function showTime() {
            second--;
            time.text(second + "");
            if (second <= 0) {
                location.href = "login.jsp";
            }
        }
        setInterval(showTime, 1000);
    </script>
    <!--引入页脚-->
    <jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>