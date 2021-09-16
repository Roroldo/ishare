<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <!-- Bootstrap -->
    <link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="css/common.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-migrate-1.0.0.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script>
        function findPassword() {
            alert("功能尚待实现...");
        }
    </script>
</head>
<body>
    <div class="wrap">
        <jsp:include page="/header.jsp"></jsp:include>
        <div id="main">
            <div class="container" style="width: 400px;">
                <h3 style="text-align: center;">用户登录</h3>
                <form action="${pageContext.request.contextPath}/user?method=login" method="post">
                    <div class="form-group">
                        <label for="user">用户名：</label>
                        <input type="text" name="username" class="form-control" id="user" placeholder="请输入用户名" value="${info.data.username}"/>
                    </div>

                    <div class="form-group">
                        <label for="password">密码：</label>
                        <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码" value="${info.data.password}"/>
                    </div>

                    <div class="form-inline">
                        <label for="verifycode">验证码：</label>
                        <input type="text" name="verifycode" class="form-control" id="verifycode" placeholder="请输入验证码" style="width: 120px;"/>
                        <a href="javascript:refreshCode()">
                            <img src="${pageContext.request.contextPath}/checkCode" title="看不清点击刷新" id="vcode" alt=""/>
                            <script type="text/javascript">
                                // 切换验证码
                                function refreshCode() {
                                    // 获取验证码图片对象
                                    var vcode = $("#vcode");
                                    // 设置其src属性，加上时间戳
                                    vcode.prop("src", "${pageContext.request.contextPath}/checkCode?time=" + new Date().getTime());
                                }
                            </script>
                        </a>
                    </div>

                    <div class="form-group" style="margin-top:10px">
                        <input type="checkbox" name="autoLogin" value="true"> &nbsp;
                        <span><font color="gray">7天内自动登录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="javascript:findPassword()">找回密码</a> | <a href="register.jsp">没有账号？注册</a></font>
                </span>
                    </div>
                    <hr/>
                    <div class="form-group" style="text-align: center;">
                        <input class="btn btn btn-primary" type="submit" value="登录">
                    </div>
                </form>
                <!-- 出错显示的信息框 -->
                <div class="alert alert-warning alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" >
                        <span>&times;</span>
                    </button>
                    <strong>${info.getErrorMsg()}</strong>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>