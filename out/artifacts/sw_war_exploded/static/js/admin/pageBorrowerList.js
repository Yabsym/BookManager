layui.use('table', function () {
    var table = layui.table;
    var reload = table.render({
        id: "ListBorrowerInf",
        elem: "#borrowerInf",
        url: "/admin/getListDatBorrower?t=" + new Date().getTime(),
        method: "post",
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['prev', 'page', 'next', 'count', 'skip', 'limit'], //自定义分页布局
            curr: 1, //设定初始在第 5 页
            groups: 5, //只显示 1 个连续页码
            first: false, //不显示首页
            last: false //不显示尾页
        },
        limit: 15,
        toolbar: '#toolbarBorrower',
        cols: [[
            {type: 'checkbox'},
            {field: 'borrowerAccount', title: '登陆账号', align: 'center', sort: true},
            {field: 'borrowerName', title: '用户名', align: 'center'},
            {field: 'sex', title: '性别', align: 'center'},
            {field: 'phone', title: '联系电话', align: 'center', sort: true},
            {field: 'maxBook', title: '最大借阅量', align: 'center'},
            {field: 'borrowBook', title: '当前借阅量', align: 'center'},
            {field: 'borrowerID', title: '身份证号', align: 'center'},
            {field: 'borrowerAddress', title: '家庭住址', align: 'center'}
        ]]
    });

    table.on('toolbar(borrowerInf)', function (obj) {
        var checkStatus = table.checkStatus("ListBorrowerInf");
        var data = checkStatus.data;
        switch (obj.event) {
            case 'modifyBorrower':
                if (data.length > 1) {
                    layer.alert('只能选择一个数据哦');
                } else if (data.length === 0) {
                    layer.alert('选择需要修改的数据');
                } else {
                    $.ajax({
                        type: 'post',
                        url: '/admin/modifyBorrowerInf',
                        data: data[0],
                        dataType: 'json',
                        success: function (data) {
                            if (data.state === "success")
                                xadmin.open("修改用户", "/admin/pageModifyBorrower", 430, 600);
                            else
                                layui.msg("error");
                        }
                    });
                }
                break;
            //自定义头工具栏右侧图标 - 提示
            case 'deleteBorrower':
                if (data.length === 0) {
                    layer.alert('选择需要删除的数据');
                } else {
                    var borrower = [];
                    data.forEach(function (n, i) {
                        borrower.push(n.borrowerAccount);
                    });
                    $.ajax({
                        type: 'post',
                        url: '/admin/deleteBorrowers',
                        data: {borrower: borrower},
                        dataType: 'json',
                        success: function (data) {
                            alert(data.msg);
                            location.replace(location);
                        }
                    });
                }
                break;
        }
    });
});