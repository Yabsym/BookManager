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
    <script type="text/javascript" src="../../../static/js/admin/pageAdminList.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script> var is_remember = false;</script>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <script id="toolbarAdmin" type="text/html">
                    <div class="layui-card-header">
                        <button class="layui-btn layui-btn-sm layui-btn-normal" onclick="location.reload()" title="刷新">
                            刷新
                        </button>
                    </div>
                </script>
                <div class="layui-card-body ">
                    <table class="layui-hide" id="adminInf"></table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
