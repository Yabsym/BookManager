<%@ page import="com.absym.entity.Borrower" %>
<%--
  Created by IntelliJ IDEA.
  User: Absym
  Date: 2019/12/25
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>欢迎进入管理员后台</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" href="../../static/css/font.css">
    <link rel="stylesheet" href="../../static/lib/xadmin/xadmin.css">
    <!-- <link rel="stylesheet" href="./css/theme5.css"> -->
    <script src="../../static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../../static/lib/xadmin/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script> var is_remember = false;</script>
</head>
<%
    Borrower borrower = (Borrower) request.getSession().getAttribute("borrower");
%>
<body class="index">
<div class="container">
    <div class="logo"><a href="/borrower">欢迎借阅图书</a></div>
    <div class="left_open"><a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
    </div>
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:"><%out.print(borrower.getBorrowerName());%></a>
            <dl class="layui-nav-child">
                <dd><a onclick="xadmin.open('个人信息','/borrower/pagePersonal',500,600)">个人信息</a></dd>
                <dd><a href="/login/loginout">退出</a></dd>
            </dl>
        </li>
    </ul>
</div>
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a onclick="xadmin.add_tab('图书列表','/borrower/pageBookList')">
                    <i class="iconfont">&#xe6a7;</i><cite>图书列表</cite>
                </a>
            </li>
            <li>
                <a onclick="xadmin.add_tab('借阅列表','/borrower/pageBorrowList')">
                    <i class="iconfont">&#xe6a7;</i><cite>借阅列表</cite>
                </a>
            </li>
        </ul>
    </div>
</div>

<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="home">
                <i class="layui-icon">&#xe68e;</i>我的桌面
            </li>
        </ul>
        <div class="layui-unselect layui-form-select layui-form-selected" id="tab_right">
            <dl>
                <dd data-type="this">关闭当前</dd>
                <dd data-type="other">关闭其它</dd>
                <dd data-type="all">关闭全部</dd>
            </dl>
        </div>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='../../desktop.jsp' frameborder="0" scrolling="no" class="x-iframe"></iframe>
            </div>
        </div>
        <div id="tab_show"></div>
    </div>
</div>
<div class="page-content-bg"></div>
<style id="theme_style"></style>
</body>

</html>