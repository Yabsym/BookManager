layui.use('table', function () {
    var table = layui.table;
    table.render({
        elem: "#borrowInf",
        url: "/borrower/getListDatBorrow?t=" + new Date().getTime(),
        method: "post",
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['prev', 'page', 'next', 'count', 'skip', 'limit'], //自定义分页布局
            curr: 1, //设定初始在第 5 页
            groups: 5, //只显示 1 个连续页码
            first: false, //不显示首页
            last: false //不显示尾页
        },
        limit: 20,
        toolbar: '#toolbarBorrow',
        cols: [[
            {type: 'checkbox'},
            {field: 'borrowID', title: '借阅流水编号', align: 'center', sort: true},
            {field: 'borrower', title: '借阅人账号', align: 'center', sort: true},
            {field: 'bookISBN', title: 'ISBN', align: 'center'},
            {field: 'borrowTime', title: '借阅时间', align: 'center', sort: true}
        ]]
    });

    table.on('toolbar(borrowInf)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        var data = checkStatus.data;
        switch (obj.event) {
            case 'deleteBorrow':
                if (data.length === 0) {
                    layer.alert('选择需要归还的书籍');
                } else {
                    var borrows = [];
                    data.forEach(function (n, i) {
                        borrows.push(n.borrowID);
                    });
                    $.ajax({
                        type: 'post',
                        url: '/borrower/deleteBorrow',
                        data: {borrows: borrows},
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
});