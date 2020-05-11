<%@ page import="com.absym.entity.Book" %><%--
  Created by IntelliJ IDEA.
  User: Absym
  Date: 2019/12/24
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String type = (String) request.getSession().getAttribute("bookType");
    request.getSession().removeAttribute("bookType");
    Book book = new Book();
    if (type != null) {
        if ("modifyBookInf".equals(type)) {
            book = (Book) request.getSession().getAttribute("modifyBookInf");
        }
    }
%>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>书籍信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="../../../static/css/font.css">
    <link rel="stylesheet" href="../../../static/lib/xadmin/xadmin.css">
    <script type="text/javascript" src="../../../static/lib/jquery.min.js"></script>
    <script type="text/javascript" src="../../../static/lib/xadmin/xadmin.js"></script>
    <script type="text/javascript" src="../../../static/lib/layui/layui.js"></script>
</head>
<body>
<blockquote class="layui-elem-quote layui-text">书籍信息</blockquote>
<form class="layui-form" id="form_dat" action="">
    <div class="layui-form-item">
        <label class="layui-form-label"><span class="x-red">*</span>书籍ISBN</label>
        <div class="layui-input-inline">
            <label>
                <input name="bookISBN" <%if(!book.getBookISBN().isEmpty())  out.print("readonly = 'readonly'");%>
                       class="layui-input" type="text" autocomplete="off" value= <%out.print(book.getBookISBN());%>>
            </label>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">书名</label>
        <div class="layui-input-block">
            <label>
                <input name="bookName" class="layui-input" type="text" autocomplete="off"
                       value= <%out.print(book.getBookName());%>>
            </label>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label"><span class="x-red">*</span>类型</label>
        <div class="layui-input-block">
            <label>
                <select name="type">
                    <option value="A" <%if ("A".equals(book.getType())) out.print("selected='selected'");%>>A
                        马克思主义\列宁主义\毛泽东思想\邓小平理论
                    </option>
                    <option value="B" <%if ("B".equals(book.getType())) out.print("selected='selected'");%>>B 哲学、宗教
                    </option>
                    <option value="C" <%if ("C".equals(book.getType())) out.print("selected='selected'");%>>C 社会科学总论
                    </option>
                    <option value="D" <%if ("D".equals(book.getType())) out.print("selected='selected'");%>>D 政治、法律
                    </option>
                    <option value="E" <%if ("E".equals(book.getType())) out.print("selected='selected'");%>>E 军事
                    </option>
                    <option value="F" <%if ("F".equals(book.getType())) out.print("selected='selected'");%>>F 经济
                    </option>
                    <option value="G" <%if ("G".equals(book.getType())) out.print("selected='selected'");%>>G
                        文化、科学、教育、体育
                    </option>
                    <option value="H" <%if ("H".equals(book.getType())) out.print("selected='selected'");%>>H 语言、文字
                    </option>
                    <option value="I" <%if ("I".equals(book.getType())) out.print("selected='selected'");%>>I 文学
                    </option>
                    <option value="J" <%if ("J".equals(book.getType())) out.print("selected='selected'");%>>J 艺术
                    </option>
                    <option value="K" <%if ("K".equals(book.getType())) out.print("selected='selected'");%>>K 历史、地理
                    </option>
                    <option value="N" <%if ("N".equals(book.getType())) out.print("selected='selected'");%>>N 自然科学总论
                    </option>
                    <option value="O" <%if ("O".equals(book.getType())) out.print("selected='selected'");%>>O 数理科学和化学
                    </option>
                    <option value="P" <%if ("P".equals(book.getType())) out.print("selected='selected'");%>>P 天文学、地球科学
                    </option>
                    <option value="Q" <%if ("Q".equals(book.getType())) out.print("selected='selected'");%>>Q 生物科学
                    </option>
                    <option value="R" <%if ("R".equals(book.getType())) out.print("selected='selected'");%>>R 医药、卫生
                    </option>
                    <option value="S" <%if ("S".equals(book.getType())) out.print("selected='selected'");%>>S 农业科学
                    </option>
                    <option value="T" <%if ("T".equals(book.getType())) out.print("selected='selected'");%>>T 工业技术
                    </option>
                    <option value="U" <%if ("U".equals(book.getType())) out.print("selected='selected'");%>>U 交通运输
                    </option>
                    <option value="V" <%if ("V".equals(book.getType())) out.print("selected='selected'");%>>V 航空、航天
                    </option>
                    <option value="X" <%if ("X".equals(book.getType())) out.print("selected='selected'");%>>X
                        环境科学、劳动保护科学(安全科学)
                    </option>
                    <option value="Z" <%if ("Z".equals(book.getType())) out.print("selected='selected'");%>>Z 综合性图书
                    </option>
                </select>
            </label>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label"><span class="x-red">*</span>本/套</label>
        <div class="layui-input-inline">
            <label>
                <input name="per" class="layui-input" type="text" autocomplete="off"
                       value= <%out.print(book.getPer());%>>
            </label>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label"><span class="x-red">*</span>书本售价</label>
        <div class="layui-input-inline">
            <label>
                <input name="price" class="layui-input" type="text" autocomplete="off"
                       value= <%out.print(book.getPrice());%>>
            </label>
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label"><span class="x-red">*</span>当前库存</label>
        <div class="layui-input-inline">
            <label>
                <input name="inventoryNum" class="layui-input" type="text" autocomplete="off"
                       value= <%out.print(book.getInventoryNum());%>>
            </label>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label"><span class="x-red">*</span>所有库存</label>
        <div class="layui-input-inline">
            <label><input name="allNum" class="layui-input" type="text" autocomplete="off"
                          value= <%out.print(book.getAllNum());%>>
            </label>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label"><span class="x-red">*</span>出版社</label>
        <div class="layui-input-inline">
            <label><input name="publicer" class="layui-input" type="text" autocomplete="off"
                          value= <%out.print(book.getPublicer());%>>
            </label>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label"><span class="x-red">*</span>出版时间</label>
        <div class="layui-input-inline">
            <label>
                <input name="publicTime" class="layui-input" type="text" autocomplete="off"
                       value= <%out.print(book.getPublicTime());%>>
            </label>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" id="modify_submit">立即提交</button>
        </div>
    </div>
</form>
<script>
    layui.use(['jquery', 'form'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        form.render();
        $(function () {
            $("#modify_submit").click(function () {
                var dat = $("#form_dat").serialize();
                $.ajax({
                    type: 'post',
                    url: '/admin/addBookInf',
                    data: dat,
                    dataType: 'json',
                    success: function (data) {
                        alert(data.msg);
                        window.location.reload();
                    }
                })
            });
        });
    });
</script>

</body>
</html>
<%book = null;%>
