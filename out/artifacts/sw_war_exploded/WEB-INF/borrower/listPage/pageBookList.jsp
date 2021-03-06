<%--
  Created by IntelliJ IDEA.
  User: Absym
  Date: 2019/12/25
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" href="../../../static/css/font.css">
    <link rel="stylesheet" href="../../../static/lib/xadmin/xadmin.css">
    <!-- <link rel="stylesheet" href="./css/theme5.css"> -->
    <script src="../../../static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../../../static/lib/xadmin/xadmin.js"></script>
    <script type="text/javascript" src="../../../static/js/borrower/pageBookList.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <script>var is_remember = false;</script>
</head>
<body>
<div class="x-nav">
    <a class="layui-btn layui-btn-small layui-btn-normal" style="line-height:1.6em;margin-top:3px;float:right"
       onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i>
    </a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5">
                        <div class="layui-inline layui-show-xs-block"><label>
                            <input type="text" name="ISBN" placeholder="请输入ISBN" autocomplete="off" class="layui-input">
                        </label></div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="sreach"><i
                                    class="layui-icon">&#xe615;</i></button>
                        </div>
                    </form>
                </div>
                <script id="toolbarBook" type="text/html">
                    <div class="layui-card-header">
                        <button class="layui-btn layui-btn-sm layui-btn-normal" onclick="location.reload()" title="刷新">
                            刷新
                        </button>
                        <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="borrow">借阅</button>
                    </div>
                </script>
                <div class="layui-card-body ">
                    <table class="layui-hide" id="bookInf" lay-filter="bookInf"></table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
