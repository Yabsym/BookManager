// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18

layui.use(['table', 'laydate', 'table'], function () {
    var table = layui.table;
    var util = layui.util;
    table.render({
        elem: '#logInf',
        url: "/admin/getListDatLog?t=" + new Date().getTime(),
        method: "post",
        cellMinWidth: 80,
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['prev', 'page', 'next', 'count', 'skip', 'limit'], //自定义分页布局
            curr: 1, //设定初始在第 5 页
            groups: 5, //只显示 1 个连续页码
            first: false, //不显示首页
            last: false //不显示尾页
        },
        toolbar: "toolbarlogInf",
        limit: 15,
        cols: [[
            {field: 'logID', title: '日志编号', align: 'center', sort: true},
            {field: 'operator', title: '操作员', align: 'center'},
            {field: 'type', title: '操作类型', align: 'center', sort: true},
            {field: 'context', title: '操作内容', align: 'center', sort: true},
            {
                field: 'time', title: '操作时间', align: 'center', templet: function (d) {
                    return util.toDateString(d.commPosttime * 1000, "yyyy-MM-dd HH:mm:ss");
                }
            }

        ]]
    });
});

