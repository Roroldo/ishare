<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <!-- Bootstrap -->
    <link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="css/common.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-migrate-1.0.0.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script src="js/register.js" type="text/javascript"></script>

    <style>
        .error{
            color: red;
        }

    </style>
</head>
<body>
    <div class="wrap">
        <%-- 动态引入header.jsp --%>
        <jsp:include page="/header.jsp"></jsp:include>
        <div id="main">
            <div class="container" style="width: 400px;">
                <center><h3>用户注册</h3></center>
                <form method="post" id="registerForm" action="${pageContext.request.contextPath}/user?method=regist">
                    <div class="form-group">
                        <label for="username">用户名：</label>
                        <input type="text" class="form-control" id="username" name="username" value="${info.data.username}">
                        <span id="s_username" class="error"></span>
                    </div>

                    <div class="form-group">
                        <label for="password">密码：</label>
                        <input type="password" class="form-control" id="password" name="password" value="${info.data.password}">
                        <span id="s_password" class="error"></span>
                    </div>

                    <div class="form-group">
                        <label for="cf_password">确认密码：</label>
                        <input type="password" class="form-control" id="cf_password" value="${info.data.password}">
                        <span id="ss_password" class="error"></span>
                    </div>

                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="text" class="form-control" id="email" name="email" value="${info.data.email}"/>
                        <span id="s_email" class="error"></span>
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

                    <div class="form-group" style="text-align: center; margin-top: 10px">
                        <input class="btn btn-primary" type="submit" value="提交" />
                        <input class="btn btn-default" type="reset" value="重置" />
                    </div>
                </form>

                <!-- 出错显示的信息框 -->
                <div class="alert alert-warning alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" >
                        <span>&times;</span>
                    </button>
                    <strong>${info.errorMsg}</strong>
                </div>
            </div>
        </div>
    </div>
    <%-- 引入header.jsp --%>
    <jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>