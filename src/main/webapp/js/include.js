/*通过ajax请求向服务器拿到页头和页脚*/
$(function () {
    $.get("header.jsp",function (data) {
        $("#header").html(data);
    });
    $.get("footer.jsp",function (data) {
        $("#footer").html(data);
    });
});