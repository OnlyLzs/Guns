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
    var Product = {
        tableId: "productTable",    //表格id
        condition: {
            name: "",
            deptId: "",
            timeLimit: ""
        }
    };

    /**
     * 初始化表格的列
     */
    Product.initColumn = function () {
    	console.log("Feng.ctxPath: " + Feng.ctxPath)
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: 'id'},
            {field: 'numb', sort: true, title: '商品编号'},
            {field: 'store_name', sort: true, title: '归属店铺'},
            {field: 'target_city_name', sort: true, title: '目标城市'},
            {field: 'start_city', sort: true, title: '出发城市'},
            {field: 'title', sort: true, title: '标题'},
            {field: 'description', sort: true, title: '描述'},
            {field: 'price', sort: true, title: '价格'},
            {field: 'type', sort: true, title: '类型'},
            {field: 'status', sort: true, templet: '#statusTpl', title: '状态'},
            {field: 'create_time', sort: true, title: '创建时间'},
            {field: 'update_time', sort: true, title: '更新时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 选择部门时
     */
    Product.onClickDept = function (e, treeId, treeNode) {
        Product.condition.deptId = treeNode.id;
        Product.search();
    };

    /**
     * 点击查询按钮
     */
    Product.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(Product.tableId, {where: queryData});
    };

    /**
     * 弹出添加商品对话框
     */
    Product.openAddProduct = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            area: ['893px', '600px'],
            fixed: false, //s不固定
            maxmin: true,
            title: '添加商品',
            content: Feng.ctxPath + '/wedding/app_product/product_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Product.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Product.exportExcel = function () {
        var checkRows = table.checkStatus(Product.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑商品按钮时
     *
     * @param data 点击按钮时候的行数据
     */
    Product.onEditProduct = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            area: ['893px', '600px'],
            fixed: false, //s不固定
            maxmin: true,
            title: '编辑商品',
            content: Feng.ctxPath + '/wedding/app_product/product_edit?productId=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Product.tableId);
            }
        });
    };

    /**
     * 点击删除商品按钮
     *
     * @param data 点击按钮时候的行数据
     */
    Product.onDeleteProduct = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wedding/app_product/delete", function () {
                table.reload(Product.tableId);
                Feng.success("删除成功!");
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("productId", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除商品" + data.title + "?", operation);
    };

    /**
     * 置为推荐
     *
     * @param data 点击按钮时候的行数据
     */
    Product.recommend = function (data) {
    	 var operation = function () {
             var ajax = new $ax(Feng.ctxPath + "/wedding/app_product/recommend", function () {
                 table.reload(Product.tableId);
                 Feng.success("操作成功!");
             }, function (data) {
                 Feng.error("操作失败!" + data.responseJSON.message + "!");
             });
             ajax.set("productId", data.id);
             ajax.start();
         };
         Feng.confirm("是否置为推荐：" + data.title + "?", operation);
    };

   
    /**
     * 修改商品状态
     *
     * @param userId 用户id
     * @param checked 是否选中（true,false），选中就是解锁用户，未选中就是锁定用户
     */
    Product.changeProductStatus = function (productId, checked) {
        if (checked) {
            var ajax = new $ax(Feng.ctxPath + "/wedding/app_product/status", function (data) {
                Feng.success("解除冻结成功!");
            }, function (data) {
                Feng.error("解除冻结失败!");
                table.reload(Product.tableId);
            });
            ajax.set("productId", productId);
            ajax.set("status", checked);
            ajax.start();
        } else {
            var ajax = new $ax(Feng.ctxPath + "/wedding/app_product/status", function (data) {
                Feng.success("冻结成功!");
            }, function (data) {
                Feng.error("冻结失败!" + data.responseJSON.message + "!");
                table.reload(Product.tableId);
            });
            ajax.set("productId", productId);
            ajax.set("status", checked);
            ajax.start();
        }
    };

    // s渲染表格
    var tableResult = table.render({
        elem: '#' + Product.tableId,
        url: Feng.ctxPath + '/wedding/app_product/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Product.initColumn()
    });

    //s渲染时间选择框
    laydate.render({
        elem: '#timeLimit',
        range: true,
        max: Feng.currentDate()
    });

    //s初始化左侧部门树
    var ztree = new $ZTree("deptTree", "/dept/tree");
    ztree.bindOnClick(Product.onClickDept);
    ztree.init();

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Product.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Product.openAddProduct();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Product.exportExcel();
    });

    // s工具条点击事件
    table.on('tool(' + Product.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Product.onEditProduct(data);
        } else if (layEvent === 'delete') {
            Product.onDeleteProduct(data);
        } else if (layEvent === 'recommend') {
            Product.recommend(data);
        } else if (layEvent === 'reset') {
            Product.resetPassword(data);
        }
    });

    // 修改product状态
    form.on('switch(status)', function (obj) {

        var productId = obj.elem.value;
        var checked = obj.elem.checked ? true : false;

        Product.changeProductStatus(productId, checked);
    });

});
