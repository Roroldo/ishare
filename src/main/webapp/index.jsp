<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <!-- Bootstrap -->
    <link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="css/common.css" rel="stylesheet">
    <link type="text/css" href="css/index.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-migrate-1.0.0.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script>
        $(function () {
            // 就业体系首页课程信息展示
            $.post("course?method=findAllByCid", {cid:3}, function (data) {
                // 零基础就业
                var lis1 = "";
                for (var i = 0; i < 4; i++) {
                    var li = '<div class="col-md-3">\n' +
                        '                    <a href="${pageContext.request.contextPath}/course?method=findOne&csId='+ data[i].csId +'">\n' +
                        '                        <img src="'+ data[i].csImg +'" class="img-responsive">\n' +
                        '                    </a>\n' +
                        '                </div>';
                    lis1 += li;
                }
                // 插入到零基础就业里面
                $("#study").html(lis1);
                var lis2 = "";
                // 职场提升
                for (var i = 4; i < data.length; i++) {
                    var li = '<div class="col-md-3">\n' +
                        '                    <a href="${pageContext.request.contextPath}/course?method=findOne&csId='+ data[i].csId +'">\n' +
                        '                        <img src="'+ data[i].csImg +'" class="img-responsive">\n' +
                        '                    </a>\n' +
                        '                </div>';
                    lis2 += li;
                }
                // 插入到职场提升里面
                $("#job").html(lis2);
            });
        });
    </script>
<body>
<div class="wrap">
    <%@ include file="header.jsp"%>
    <div id="main">
        <!--巨幕以及轮播图-->
        <div class="jumbotron" style="font-size:12px; color: #2aabd2; height: 350px;">
            <div class="container-fluid">
                <div class="row">
                    <div class="jumbotron col-md-4" style="height: 200px;">
                        <h2>Hello, world!</h2>
                        <p>
                            <font size="2px">
                                这是一个免费分享 IT 课程的学习网站，希望大家都能找到自己想要的课程资源~<br>
                            </font>
                        </p>
                        <p><a class="btn btn-primary btn-lg" href="rule.jsp" role="button">Learn more</a></p>
                    </div>

                    <div class="col-md-8">
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                            <!-- Indicators -->
                            <ol class="carousel-indicators">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            </ol>

                            <!-- Wrapper for slides -->
                            <div class="carousel-inner" role="listbox">
                                <div class="item active">
                                    <img src="img/ban_2.jpg" alt="...">
                                    <div class="carousel-caption"></div>
                                </div>
                                <div class="item">
                                    <img src="img/ban_1.jpg" alt="...">
                                    <div class="carousel-caption"></div>
                                </div>
                                <div class="item">
                                    <img src="img/ban_3.jpg" alt="...">
                                    <div class="carousel-caption"></div>
                                </div>
                            </div>
                            <!-- Controls -->
                            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--页面展示-->
        <div class="container" style="margin-top: 50px">
            <div class="row">
                <span class="course_title"><b>零基础就业</b></span>
                <span class="course_subtitle">从入门到具备一年开发经验</span>
                <hr>
            </div>
            <!-- 体系课程 -->
            <div class="row index_course " id="study">
                <%--就业体系课程--%>
            </div>
            <br><br>

            <!--职场课程-->
            <div class="row">
                <span class="course_title"><b>职场提升</b></span>
                <span class="course_subtitle">1年以上开发经验系统成长</span>
                <hr>
            </div>

            <div class="row index_course" id="job">
                <%--职场课程内容--%>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>