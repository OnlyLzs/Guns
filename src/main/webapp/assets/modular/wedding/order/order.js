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
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: '订单id'},
            {field: 'order_num', sort: true, title: '订单编号'},
            {field: 'user_id', sort: true, title: '用户id'},
            {field: 'product_name', sort: true, title: '商品名称'},
            {field: 'store_name', sort: true, title: '承办店铺'},
            {field: 'start_city', sort: true, title: '出发城市'},
            {field: 'start_date', sort: true, title: '出发日期'},
            {field: 'buyer_name', sort: true, title: '联系人姓名'},
            {field: 'buyer_mobile', sort: true, title: '联系人电话'},
            {field: 'buyer_email', sort: true, title: '联系人邮箱'},
            {field: 'price', sort: true, title: '价格'},
            {field: 'create_time', sort: true, title: '创建时间'},
            {field: 'update_time', sort: true, title: '更新时间'},
            {field: 'status', sort: true, templet: '#statusTpl', title: '状态'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 100}
        ]];
    };

    /**
     * 点击查询按钮
     */
    MgrUser.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        queryData['timeLimit'] = $("#timeLimit").val();
        queryData['orderStatus'] = $("#orderStatus").val();
        table.reload(MgrUser.tableId, {where: queryData});
    };

    /**
     * 显示详情对话框
     */
    MgrUser.onDetail = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '订单详情',
            area: ['893px', '600px'],
            fixed: false, //s不固定
            maxmin: true,
            content: Feng.ctxPath + '/wedding/app_order/detail/'+data.id
        });
    };

    /**
     * 导出excel按钮
     */
    MgrUser.exportExcel = function () {
        var checkRows = table.checkStatus(MgrUser.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };


    // 渲染表格
    var tableResult = table.render({
        elem: '#' + MgrUser.tableId,
        url: Feng.ctxPath + '/wedding/app_order/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: MgrUser.initColumn()
    });

    //渲染时间选择框
    laydate.render({
        elem: '#timeLimit',
        range: true,
        max: Feng.currentDate()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        MgrUser.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        MgrUser.openAddUser();
    });

    // 导出excel
    $('#btnExp').click(function () {
        MgrUser.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + MgrUser.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'detail') {
            MgrUser.onDetail(data);
        } 
    });

    // 修改user状态
    form.on('switch(status)', function (obj) {

        var userId = obj.elem.value;
        var checked = obj.elem.checked ? true : false;

        MgrUser.changeUserStatus(userId, checked);
    });

});
