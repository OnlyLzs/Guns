layui.use(['layer', 'form', 'table', 'ztree', 'laydate', 'admin', 'ax'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ZTree = layui.ztree;
    var $ax = layui.ax;
    var laydate = layui.laydate;
    var admin = layui.admin;

    /**
     * 系统管理--用户管理
     */
    var MgrUser = {
        tableId: "userTable",    //表格id
        condition: {
            name: "",
            deptId: "",
            timeLimit: ""
        }
    };

    /**
     * 初始化表格的列
     */
    MgrUser.initColumn = function () {
        return [[
            {type: 'numbers'},
            {field: 'id', hide: true, sort: true, title: 'id'},
            {field: 'name', sort: true, title: '姓名'},
            {field: 'mobile', sort: true, title: '电话'},
            {field: 'identity_card', sort: true, title: '身份证号'},
            {field: 'passport', sort: true, title: '护照号码'}
        ]];
    };
 

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + MgrUser.tableId,
        url: Feng.ctxPath + '/wedding/app_order/detail/show/'+document.getElementById("orderId").value,
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: MgrUser.initColumn()
    });


});
