<%@ page import="com.absym.entity.Admin" %>
<%@ page import="com.absym.entity.User" %>
<%--
  Created by IntelliJ IDEA.
  User: Absym
  Date: 2019/12/24
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>个人信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="../../../static/css/font.css">
    <link rel="stylesheet" href="../../../static/lib/xadmin/xadmin.css">
    <!-- <link rel="stylesheet" href="./css/theme5.css"> -->
    <script src="../../../static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../../../static/lib/jquery.min.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<%
    User user = (User) request.getSession().getAttribute("user");
    Admin admin = (Admin) request.getSession().getAttribute("admin");
%>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <blockquote class="layui-elem-quote layui-text">个人信息</blockquote>
        <form id="dat_form" class="layui-form">

            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>账号</label>
                <div class="layui-input-inline">
                    <input type="text" name="adminAccount" required="" autocomplete="off"
                           class="layui-input" readonly="readonly" value= <%out.print(admin.getAdminAccount());%>>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>用户名</label>
                <div class="layui-input-inline">
                    <input type="text" name="adminName" required="" autocomplete="off"
                           class="layui-input" value= <%out.print(admin.getAdminName());%>>
                </div>
            </div>


            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>邮箱</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_email" name="email" required="" lay-verify="email"
                           autocomplete="off" class="layui-input" value= <%out.print(admin.getEmail());%>>
                </div>
            </div>


            <div class="layui-form-item">
                <label for="L_email" class="layui-form-label"><span class="x-red">*</span>身份证</label>
                <div class="layui-input-inline">
                    <input type="text" id="idCard" name="adminId" required="" lay-verify="identity"
                           autocomplete="off" class="layui-input" value= <%out.print(admin.getAdminId());%>>
                </div>
            </div>


            <div class="layui-form-item">
                <label for="L_email" class="layui-form-label"><span class="x-red">*</span>电话号码</label>
                <div class="layui-input-inline">
                    <input type="text" id="phone" name="phone" required="" lay-verify="phone"
                           autocomplete="off" class="layui-input" value= <%out.print(admin.getPhone());%>>
                </div>
            </div>


            <div class="layui-form-item">
                <label for="L_email" class="layui-form-label"><span class="x-red">*</span>权限</label>
                <div class="layui-input-inline">
                    <input type="text" id="power" name="power" required=""
                           autocomplete="off" class="layui-input" readonly="readonly"
                           value= <%out.print(admin.getPower());%>>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>以前密码</label>
                <div class="layui-input-inline">
                    <input type="password" id="loldpass" name="oldpassword" required="" autocomplete="off"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>新的密码</label>
                <div class="layui-input-inline">
                    <input type="password" id="lpass" name="newpassword" required="" lay-verify="pass"
                           autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <button id="submit" class="layui-btn layui-btn-normal" lay-filter="modify">提交</button>
            </div>
        </form>
    </div>
</div>
<script>
    $(function () {
        $("#submit").click(function () {
            var dat = $("#dat_form").serialize();
            $.ajax({
                type: 'post',
                url: '/admin/addPersonalInf',
                data: dat,
                dataType: 'json',
                success: function (data) {
                    alert(data.msg);
                }
            })
        });
    });

</script>
</body>

</html>
