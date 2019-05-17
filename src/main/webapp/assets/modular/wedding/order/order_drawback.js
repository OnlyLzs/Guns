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
    var Order = {
        tableId: "orderTable",    //表格id
        condition: {
            name: "",
            deptId: "",
            timeLimit: ""
        }
    };

    /**
     * 初始化表格的列
     */
    Order.initColumn = function () {
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
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 280 }
        ]];
    };

    /**
     * 点击查询按钮
     */
    Order.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        queryData['timeLimit'] = $("#timeLimit").val();
        queryData['orderStatus'] = $("#orderStatus").val();
        table.reload(Order.tableId, {where: queryData});
    };

    

    /**
     * 导出excel按钮
     */
    Order.exportExcel = function () {
        var checkRows = table.checkStatus(Order.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击同意退款按钮时
     *
     * @param data 点击按钮时候的行数据
     */
    Order.onAgree = function (data) {
    	 var ajax = new $ax(Feng.ctxPath + "/wedding/app_order/status", function (data) {
             Feng.success("退款成功!");
             table.reload(Order.tableId);
         }, function (data) {
             Feng.error("退款失败!");
             table.reload(Order.tableId);
         });
         ajax.set("orderId", data.id);
         ajax.set("status", "6");
         ajax.start();
    };

    /**
     * 点击拒绝退款按钮
     *
     * @param data 点击按钮时候的行数据
     */
    Order.onRefuse = function (data) {
    	var ajax = new $ax(Feng.ctxPath + "/wedding/app_order/status", function (data) {
            Feng.success("拒绝退款成功!");
            table.reload(Order.tableId);
        }, function (data) {
            Feng.error("退款失败!");
            table.reload(Order.tableId);
        });
        ajax.set("orderId", data.id);
        ajax.set("status", "3");
        ajax.start();
    };

    /**
     * 查看订单详情
     *
     * @param data 点击按钮时候的行数据
     */
    Order.onDetail = function (data) {
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

    

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Order.tableId,
        url: Feng.ctxPath + '/wedding/app_order/list/drawback',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Order.initColumn()
    });

    //渲染时间选择框
    laydate.render({
        elem: '#timeLimit',
        range: true,
        max: Feng.currentDate()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Order.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Order.openAddUser();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Order.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Order.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'agree') {
            Order.onAgree(data);
        } else if (layEvent === 'refuse') {
            Order.onRefuse(data);
        } else if (layEvent === 'detail') {
            Order.onDetail(data);
        }
    });

});
