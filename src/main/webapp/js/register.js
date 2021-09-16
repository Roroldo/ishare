/*校验注册时填写的字段*/
// 校验表单
function checkUsername() {
    var username = $("#username").val();
    var reg_username = /^\w{4,12}$/;
    var flag = reg_username.test(username);
    if (flag) {
        $("#username").css("border", "1px solid green");
        $("#s_username").text("");
    } else {
        $("#username").css("border", "1px solid red");
        $("#s_username").text("用户名格式不正确！");
    }
    return flag;
}

function checkPassword() {
    var password = $("#password").val();
    var reg_password = /^\w{4,12}$/;
    var flag = reg_password.test(password);
    if (flag) {
        $("#password").css("border", "1px solid green");
        $("#s_password").text("");
    } else {
        $("#password").css("border", "1px solid red");
        $("#s_password").text("密码太短！");
    }
    return flag;
}

function checkCfPassword() {
    var cf_password  = $("#cf_password").val();
    var password = $("#password").val();
    if ((cf_password === password) && (cf_password !== "" && password !== "") ) {
        $("#cf_password").css("border", "1px solid green");
        $("#ss_password").text("");
        return true;
    } else{
        $("#cf_password").css("border", "1px solid red");
        $("#ss_password").text("两次填写密码不一致！");
        return false;
    }
}

function checkEmail() {
    var email = $("#email").val();
    var reg_email = /^\w+@\w+\.\w+$/;
    var flag = reg_email.test(email);
    if (flag) {
        $("#email").css("border", "1px solid green");
        $("#s_email").text("");
    } else {
        $("#email").css("border", "1px solid red");
        $("#s_email").text("邮箱格式不正确，请填写正确的邮箱以便激活账号！");
    }
    return flag;
}

$(function () {
    $("#registerForm").submit(function () {
        // 发送消息到服务器
        if (checkUsername() && checkPassword() && checkEmail() && checkCfPassword()) {
            return true;
        }
        return false;
    });
    // 绑定失去焦点事件
    $("#username").blur(checkUsername);
    $("#password").blur(checkPassword);
    $("#cf_password").blur(checkCfPassword);
    $("#email").blur(checkEmail);
});


