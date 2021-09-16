<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>页角</title>
    <link type="text/css" rel="stylesheet" href="css/footer.css"/>
</head>
<body>
    <hr>
    <div class="footer_text">免责声明：</div>
    <div class="footer_text">
        ishare 所发布的一切课程资料仅限用于学习和研究目的；
        不得将上述内容用于商业或者非法用途，否则，一切后果请用户自负。本站信息来自网络，版权争议与本站无关。您必须在下载后的24个小时之内，从您的电脑中彻底删除上述内容。如果您喜欢该课程并且有能力的话，请支持正版课程，
        购买正版课程，得到更好的正版服务。
        如有侵权请邮件与我们联系处理。
    </div>
    <div style="float: right" class="footer_text">Mail To:roroldo@gmail.com</div><br>
    <hr>

    <div style="float: left" class="small_text">
        Powered by <b>roroldo!</b> <br>
        Copyright © 2001-2020, Tencent Cloud.
    </div>

    <div style="float: right;">
        <a href="https://gitee.com/Roroldo/ImgRepo/raw/master/20201112180307.png" target="_blank">赞助本站</a> | 支持名单 | <a href="mailto:roroldo@gmail.com" target="_blank">联系我们</a> | <b><a href="index.jsp">ishare - LCG - LSG</a></b> ( 京ICP备16042023号 | 京公网安备 11010502030087号 )
    </div>
    <br>

    <!-- jsp插入时间   -->
    <div style="float: right">
        <font color="grey" size="2px"></font>
        GMT+8, <span id="time1"></span>
    </div>

    <!--动态更新时间-->
    <script>
        $(function () {
            function getTime() {
                $("#time1").html(new Date().toLocaleString());
            }
            // 每秒执行一次
            setInterval(getTime, 1000);
        })
    </script>
</body>
</html>