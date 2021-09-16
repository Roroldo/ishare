<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>上传课程</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>导航条</title>
    <!-- Bootstrap -->
    <link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="css/common.css" rel="stylesheet">
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
            <div class="container" style="width: 600px">
                <center><h3>上传课程</h3></center>
                <form action="${pageContext.request.contextPath}/course?method=add" class="form-horizontal" enctype="multipart/form-data" method="post">
                    <div class="form-group">
                        <label for="course_name" class="col-sm-2 control-label">课程名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="course_name" name="csName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="course_author" class="col-sm-2 control-label">主讲老师</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="course_author" name="csAuthor">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="course_detail" class="col-sm-2 control-label">课程介绍</label>
                        <div class="col-sm-10">
                            <textarea rows="5" cols="10" class="form-control" id="course_detail" name="csIntroduce"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="course_category" class="col-sm-2 control-label">课程分类</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="course_category" name="cid">

                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="course_link" class="col-sm-2 control-label">网盘链接</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="course_link" name="uri">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="course_code" class="col-sm-2 control-label">提取码</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="course_code" name="code">
                        </div>
                    </div>

                    <%--由于部署在服务器上不可用，因此就不实现了--%>
                    <%--<div class="form-group">
                        <label for="course_photo" class="col-sm-2 control-label">课程图片</label>
                        <div class="col-sm-10">
                            <input type="file" id="course_photo" name="csImg" accept="image/*">
                        </div>
                    </div>--%>

                    <div class="form-group" style="text-align: center">
                        <input class="btn btn-primary" type="submit" value="提交" />
                        <input class="btn btn-default" type="reset" value="重置" />
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div id="footer"></div>

    <script>
        /*动态加载课程分类*/
        $(function () {
            // 查询分类信息
            $.post("category?method=findAll", {}, function (data) {
                // json数据格式：[{"cid":1,"cname":"计算机基础"},{"cid":2,"cname":"编程语言"},{"cid":3,"cname":"就业体系"}]
                var ops = "";
                // 遍历数组，拼接字符串
                for (var i = 0; i < data.length; i++) {
                    var op = '<option value="'+ data[i].cid +'">'+ data[i].cname +'</option>';
                    ops += op;
                }
                // 将lis字符串设置到ul的html内容中
                $("#course_category").html(ops);
            });
        });
    </script>
</body>
</html>