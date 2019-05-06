layui.use(['layer', 'form', 'table', 'admin', 'ax'], function(){
	var $ = layui.$;
	var layer = layui.layer;
	var form = layui.form;
	var table = layui.table;
	var $ax = layui.ax;
	var admin = layui.admin;
	
	var Store = {
		tableId: "storeTable",
		condition: {
			name: ""
		}
	};
	
	Store.initColumn = function (){
		return [[
			{type: 'checkbox'},
			{field: 'id', hide: true, sort: true, title: 'id', align:'center'},
			{field: 'name', sort: true, title: '名称', align: 'center'},
			//{field: 'img_req', sort: true, title: '图片', align: 'center', templet: '<div><img src="{{d.img_req}}"/></div>'},
			{field: 'img_url', sort: true, title: '图片', align: 'center', templet: '#imgUrlTemplet'},
			{field: 'tel', sort: true, title: '电话', align: 'center'},
			{field: 'address', sort: true, title: '地址', align: 'center'},
			{field: 'open_time', sort: true, title: '开业时间', align: 'center'},
			{field: 'close_time', sort: true, title: '关闭时间', align: 'center'},
			{align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200, align: 'center'}
		]];
	};
	
	Store.search = function (){
		var queryData = {};
		queryData['name'] = $("#name").val();
		table.reload(Store.tableId, {where: queryData});
	};
	
	var tableResult = table.render({
		elem: '#' + Store.tableId,
		url: Feng.ctxPath + '/wedding/app_store/list',
		page: true,
		height: "full-158",
	    cellMinWidth: 100,
	    cols: Store.initColumn() 
	});
	
	Store.onDeleteStore = function (data){
		var operation = function (){
			var ajax = new $ax(Feng.ctxPath + "/wedding/app_store/remove", function (){
				Feng.success("删除成功!");
				table.reload(Store.tableId);
			}, function (data){
				Feng.error("删除失败!" + data.responseJSON.message + "!");
			});
			ajax.set("id", data.id);
			ajax.start();
		};
		Feng.confirm("是否删除该店铺?", operation);
	};
	
	Store.onEditStore = function (data){
		admin.putTempData('formOk', false);
		top.layui.admin.open({
            type: 2,
            title: '修改店铺',
            content: Feng.ctxPath + '/wedding/app_store/store_edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Store.tableId);
            }
        });
	};
	
	Store.openAddPicture = function(){
		admin.putTempData('formOk', false);
		top.layui.admin.open({
            type: 2,
            title: '添加轮播',
            content: Feng.ctxPath + '/wedding/app_store/store_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Store.tableId);
            }
        });
	} 
	
	//搜索按钮点击事件
	$('#btnSearch').click(function (){
		Store.search();
	});
	
	// 添加按钮点击事件
    $('#btnAdd').click(function () {
    	Store.openAddPicture();
    });
	
	//工具条点击事件
	table.on('tool(' + Store.tableId + ')', function(obj){
		var data = obj.data;
		var layEvent = obj.event;
	
		if(layEvent === 'edit'){
			Store.onEditStore(data);
		} else if (layEvent === 'delete'){
			Store.onDeleteStore(data);
		}
	});
	
	$("body").on("click", "#showDiv", function(){
		var picture = $(this).find("img");
		//页面层
	    layer.open({
	        type: 1,
	        skin: 'layui-layer-rim', //加上边框
	        area: ['80%', '80%'], //宽高
	        shadeClose: true, //开启遮罩关闭
	        end: function (index, layero) {
	            return false;
	        },
	        content: '<div style="text-align:center;"><img src="' + $(picture).attr('src') + '" /></div>'
	    });
	}); 
});