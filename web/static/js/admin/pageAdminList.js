layui.use('table', function () {
    var table = layui.table;
    table.render({
        elem: '#adminInf',
        url: "/admin/getListDatAdmin?t=" + new Date().getTime(),
        method: "post",
        cellMinWidth: 80,
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['prev', 'page', 'next', 'count', 'skip', 'limit'], //自定义分页布局
            curr: 1, //设定初始在第 5 页
            groups: 5, //只显示 1 个连续页码
            first: false, //不显示首页
            last: false //不显示尾页
        },
        limit: 15,
        toolbar: '#toolbarAdmin',
        cols: [[
            {field: 'adminAccount', title: '管理员账号', align: 'center'},
            {field: 'adminName', title: '管理员名', align: 'center'},
            {field: 'email', title: '邮箱', align: 'center'},
            {field: 'adminId', title: '身份证', align: 'center'},
            {field: 'phone', title: '电话号码', align: 'center'},
            {field: 'power', title: '权限', align: 'center'}

        ]]
    });
});
