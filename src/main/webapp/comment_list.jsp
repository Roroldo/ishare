<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>ishare 留言板</title>
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
    <link type="text/css" rel="stylesheet" href="css/rule.css">
    <style>
        a {
            text-decoration: none;
        }

        .comments {
            font-size: 30px;
        }

        .username {
            color: #2aabd2;
        }

        .comment-ctrl {
            font-size: 16px;
        }
    </style>

    <script>
        function showAnswers(cid) {
            // 发送请求
            $(".answers").html("");
            $.post("comment?method=findAnswersByCid", {cid:cid}, function (answerList) {
                if (answerList.length != 0) {
                    var lis = "";
                    for (let i = 0; i < answerList.length; i++) {
                        var li = '<div><span style="font-size: 16px"><span style="color: peru;font-size: 14px">' + answerList[i].user.username + '：</span>'+ answerList[i].content +'</span><span style="margin-left: 4px;font-size: 12px; color: gray">' + answerList[i].sendDateStr + '</span></div>';
                        lis += li;
                    }
                    showComment(cid);
                    $(".answers").append(lis);
                } else {
                    $(".answers").html("");
                    showComment(cid);
                    $(".answers").append("此留言暂无回复，有回复时会邮箱通知你的，请留意~");
                }
            });
        }

        function showComment(cid) {
            $(".oneComment").html('');
            $.post("comment?method=findCommentByCid", {cid:cid}, function (comment) {
                var row = '<span  style="font-size: 25px">' + comment.user.username + '：</span>'+ comment.content + ' 的留言回复：';
                $(".oneComment").append(row);
            });
        }

        function showSend(cid) {
            showComment(cid);
            $(".sendAnswer").html("");
            $(".sendAnswer").append('<form class="form-inline" action="${pageContext.request.contextPath}/comment?method=addAnswer" method="post">\n' +
                '                                <input type="text" class="form-control"  name="content" style="width: 400px">\n' +
                '                                <input type="hidden"  name="cid" value="' + cid + '">\n' +
                '                                <button type="submit" class="btn btn-primary">回复</button>\n' +
                '                            </form>');
        }

        function delComment(commentId) {
            if (confirm("你确认删除留言吗？")) {
                location.href = "${pageContext.request.contextPath}/comment?method=deleteCommentByCid&cid=" + commentId;
            }
        }

        function addCount(cid) {
            $(".addCount").removeProp("onclick");
            location.href = "${pageContext.request.contextPath}/comment?method=addCommentCountByCid&cid=" + cid;
        }
    </script>

</head>
<body>

    <div class="wrap">
        <jsp:include page="/header.jsp"></jsp:include>
        <div id="main">
            <div id="rule">
            <h3>ishare 留言板</h3>
            <div class="treaty">
                <p>&nbsp;留言板是资源链接失效时或寻求资源时，您可以在此模块留下您需要的资源更新或留下你需要找的资源。如果有人回复你的留言，
                    我们会有邮箱提示你及时查看！你也可以直接搜索留言内容看看资源是否到位。欢迎社区里的用户及时解答留言板的问题，互帮互助。</p>
            </div>
            <br>
            <div>
                <form class="form-inline" action="${pageContext.request.contextPath}/comment?method=addComment" method="post">
                    我要发表留言：<input type="text"  class="form-control" placeholder="请输入你的留言" name="content" style="width: 400px">
                    <button type="submit" class="btn btn-success">发表</button>
                </form>
            </div>
            <div style="margin-top: 20px">
                <form class="form-inline" action="${pageContext.request.contextPath}/comment?method=queryForList" method="post">
                    搜索留言信息：<input type="text" class="form-control " placeholder="请输入需要查找的留言信息" name="content" style="width: 400px" value="${searchContent}">
                    <button type="submit" class="btn btn-primary">搜索</button>
                </form>
            </div>
            <br/>
            <div style="margin-left: 20px">
                <h4>留言列表：</h4>
                <div class="comments" style="margin-top: 20px; margin-left: 40px">

                    <c:forEach items="${pb.list}" var="comment" varStatus="status">
                        <div style="margin-bottom: 5px">${status.count}.<span class="username" style="font-size: 25px">${comment.user.username}：</span>${comment.content}<font style="color: gray; font-size: 10px;margin-left: 5px"> ${comment.sendDateStr}</font>
                            <span class="comment-ctrl">
                                <a class="addCount" onclick="addCount(${comment.commentId})" style="color: greenyellow ; margin-left: 30px" ><span class="glyphicon glyphicon-menu-up"> </span>顶${comment.count}</a>
                                <a style="color: deeppink; padding-left: 15px"  onclick="showAnswers(${comment.commentId})"><span class="glyphicon glyphicon-retweet"> </span> 查看详情</a>
                                <a style="color: blueviolet; padding-left: 15px"  onclick="showSend(${comment.commentId})"><span class="glyphicon glyphicon-apple"> </span> 回复</a>
                                <c:if test="${comment.user.uid == user.uid}" >
                                    <a style="color: red; padding-left: 15px" onclick="delComment(${comment.commentId})" ><span class="glyphicon glyphicon-remove"> </span> 删除</a>
                                </c:if>
                            </span>
                        </div>
                    </c:forEach>
                </div>
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
                                <a href="${pageContext.request.contextPath}/comment?method=queryForList&currentPage=${pb.currentPage - 1}&rows=4&content=${searchContent}">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
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
                                        <a href="${pageContext.request.contextPath}/comment?method=queryForList&currentPage=${i}&rows=4&content=${searchContent}">
                                                ${i}
                                        </a>
                                    </li>
                                </c:if>
                                <%-- 不是当前页 --%>
                                <c:if test="${pb.currentPage != i}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/comment?method=queryForList&currentPage=${i}&rows=4&content=${searchContent}">
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
                                <a href="${pageContext.request.contextPath}/comment?method=queryForList&currentPage=${pb.currentPage + 1}&rows=4&content=${searchContent}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </c:if>

            <div class="oneComment" style="margin-top: 20px"></div>
            <div style="margin-left: 80px" class="answers">

            </div>
            <div style="margin-top: 20px; margin-left: 40px" class="sendAnswer">
            </div>
        </div>
    </div>
    <br><br>

    <jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>