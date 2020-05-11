<%--
  Created by IntelliJ IDEA.
  User: Absym
  Date: 2019/12/22
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <link rel="stylesheet" media="screen" href="./static/css/login.css">
    <script src="./static/lib/jquery.min.js"></script>
    <script src="./static/js/login/login.js"></script>
</head>
<body>
<!-- particles.js container -->
<div id="particles-js" style="display: flex;align-items: center;justify-content: center"></div>
<div class="login-page">
    <div class="login-content">
        <div class="login-tit">登录</div>
        <form id="form_dat">
            <div class="login-input"><label for="input_accout"></label><input id="input_accout" type="text"
                                                                              name="account" placeholder="请输入账号"
                                                                              autocomplete="off"></div>
            <div class="login-input"><label for="input_psw"></label><input id="input_psw" type="password"
                                                                           name="password" placeholder="请输入密码"
                                                                           autocomplete="off"></div>
            <div class="captche-input">
                <label>
                    <input type="text" id="input_captche" name="captche" placeholder="请输入验证码" autocomplete="off">
                    <img title="点击图片切换验证码" id="captcheImg" src="/login/getCaptcheCodeImg" alt="点击图片切换验证码">
                </label>
            </div>
        </form>
        <div class="login-btn">
            <div class="login-btn-left" id="reset_login"><span>重置</span></div>
            <div class="login-btn-right" id="login_request"><span>登录</span></div>
        </div>
    </div>
</div>

<script src="static/js/login/login_particles.js"></script>
<script src="static/js/login/login_app.js"></script>
</body>
</html>
