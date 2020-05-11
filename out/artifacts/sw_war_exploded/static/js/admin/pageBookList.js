layui.use('table', function () {
    var table = layui.table;
    var $ = layui.jquery;
    var searchInf = $('#search_inf').val();
    table.render({
        id: "ListBookinf",
        elem: "#bookInf",
        url: "/admin/getListDatBook?t=" + new Date().getTime(),
        method: "post",
        where: {'searchInf': searchInf, 'searchType': 'book'},
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['prev', 'page', 'next', 'count', 'skip', 'limit'], //自定义分页布局
            curr: 1, //设定初始在第 5 页
            groups: 5, //只显示 1 个连续页码
            first: false, //不显示首页
            last: false //不显示尾页
        },
        limit: 15,
        toolbar: '#toolbarBook',
        cols: [[
            {type: 'checkbox'},
            {field: 'bookISBN', title: 'ISBN', align: 'center', sort: true},
            {field: 'bookName', title: '书名', align: 'center'},
            {field: 'type', title: '类型', align: 'center', sort: true},
            {field: 'price', title: '书价', align: 'center', sort: true},
            {field: 'allNum', title: '所有库存', align: 'center'},
            {field: 'inventoryNum', title: '当前库存', align: 'center'},
            {field: 'per', title: '本/套', align: 'center'},
            {field: 'publicer', title: '出版社', align: 'center'},
            {field: 'publicTime', title: '出版时间', align: 'center'}
        ]]
    });

    table.on('toolbar(bookInf)', function (obj) {
        var checkStatus = table.checkStatus("ListBookinf");
        var data = checkStatus.data;
        switch (obj.event) {
            case 'modifyBook':
                if (data.length > 1) {
                    layer.alert('只能选择一个数据哦');
                } else if (data.length === 0) {
                    layer.alert('选择需要修改的数据');
                } else {
                    $.ajax({
                        type: 'post',
                        url: '/admin/modifyBookInf',
                        data: data[0],
                        dataType: 'json',
                        success: function (data) {
                            if (data.state === "success")
                                xadmin.open("修改书", "/admin/pageModifyBook", 430, 600);
                            else
                                layui.msg("error");
                        }
                    });
                }
                break;
            //自定义头工具栏右侧图标 - 提示
            case 'deleteBook':
                if (data.length === 0) {
                    layer.alert('选择需要删除的数据');
                } else {
                    var books = [];
                    data.forEach(function (n, i) {
                        books.push(n.bookISBN);
                    });
                    console.log(books);
                    $.ajax({
                        type: 'post',
                        url: '/admin/deleteBooks',
                        data: {books: books},
                        dataType: 'json',
                        success: function (data) {
                            location.replace(location);
                            alert(data.msg);
                        }
                    });
                }
                break;
        }
    });

    $('#search_book').on('click', function () {
        // 搜索条件
        var searchInf = $('#search_inf').val();
        table.reload('ListBookinf', {
            method: 'post',
            where: {'searchInf': searchInf, 'searchType': 'book'},
            page: {curr: 1}
        });
    });
});

