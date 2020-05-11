<%@ page import="com.absym.entity.Borrower" %>
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
    Borrower borrower = (Borrower) request.getSession().getAttribute("borrower");
%>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form id="dat_form" class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>账号</label>
                <div class="layui-input-inline">
                    <input type="text" name="borrowerAccount" required="" autocomplete="off"
                           class="layui-input" readonly="readonly" value= <%out.print(borrower.getBorrowerAccount());%>>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>用户名</label>
                <div class="layui-input-inline">
                    <input type="text" name="borrowerName" required="" autocomplete="off"
                           class="layui-input" value= <%out.print(borrower.getBorrowerName());%>>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input name="sex" title="男"
                           type="radio" <%if(borrower.getSex().isEmpty() || "男".equals(borrower.getSex())) out.print("checked=\"\"");%>
                           value="男">
                    <input name="sex" title="女" type="radio"
                           <%if("女".equals(borrower.getSex())) out.print("checked=\"\"");%>value="女">
                </div>
            </div>


            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>身份证</label>
                <div class="layui-input-inline">
                    <input type="text" id="borrowerID" name="borrowerID" required="" lay-verify="identity"
                           autocomplete="off" class="layui-input" value= <%out.print(borrower.getBorrowerID());%>>
                </div>
            </div>


            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>电话号码</label>
                <div class="layui-input-inline">
                    <input type="text" id="phone" name="phone" required="" lay-verify="phone"
                           autocomplete="off" class="layui-input" value= <%out.print(borrower.getPhone());%>>
                </div>
            </div>


            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>地址</label>
                <div class="layui-input-inline">
                    <input type="text" id="borrowerAddress" name="borrowerAddress" required=""
                           autocomplete="off" class="layui-input" value= <%out.print(borrower.getBorrowerAddress());%>>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>当前借阅</label>
                <div class="layui-input-inline">
                    <input type="text" id="borrowBook" name="borrowBook" required=""
                           autocomplete="off" class="layui-input" readonly="readonly"
                           value= <%out.print(borrower.getBorrowBook());%>>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>最大借阅</label>
                <div class="layui-input-inline">
                    <input type="text" id="maxBook" name="maxBook" required=""
                           autocomplete="off" class="layui-input" readonly="readonly"
                           value= <%out.print(borrower.getMaxBook());%>>
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
    layui.use(['jquery', 'form'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        form.render();
        $(function () {
            $("#submit").click(function () {
                var dat = $("#dat_form").serialize();
                $.ajax({
                    type: 'post',
                    url: '/borrower/modifyPersonalInf',
                    data: dat,
                    dataType: 'json',
                    success: function (data) {
                        alert(data.msg);
                    }
                })
            });
        });
    });
</script>
</body>

</html>
