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
    <script type="text/javascript" src="../../../static/lib/jquery.min.js"></script>
    <script type="text/javascript" src="../../../static/lib/xadmin/xadmin.js"></script>
    <script type="text/javascript" src="../../../static/lib/layui/layui.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <blockquote class="layui-elem-quote layui-text">借阅信息</blockquote>
        <form id="dat_form" class="layui-form">

            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>借阅人账号</label>
                <div class="layui-input-inline">
                    <input type="text" name="borrower" required="" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>书籍ISBN</label>
                <div class="layui-input-inline">
                    <input type="text" name="bookISBN" required="" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <button id="submit" class="layui-btn layui-btn-normal">提交</button>
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
                url: '/admin/addBorrowInf',
                data: dat,
                dataType: 'json',
                success: function (data) {
                    alert(data.msg);
                    window.location.reload();
                }
            })
        });
    });

</script>
</body>

</html>