layui.use(['layer', 'form', 'table', 'admin', 'ax'], function (){
	var $ = layui.$;
	var layer = layui.layer;
	var form = layui.form;
	var table = layui.table;
	var $ax = layui.ax;
	var admin = layui.admin;

	var Picture = {
		tableId: "pictureTable",
		condition: {
			imgUrl : ""
		}
	};
	
	Picture.initColumn = function () {
		return [[
			{type: 'checkbox'},
			{field: 'id', hide: true, sort: true, title: '图片id', align:'center'},
			{field: 'img_url', sort: true, title: '名称', align: 'center'},
			//{field: 'img_req', sort: true, title: '图片', align: 'center', templet: '<div><img src="{{d.img_req}}"/></div>'},
			{field: 'img_req', sort: true, title: '图片', align: 'center', templet: '#imgUrlTemplet'},
			{field: 'status', sort: true, title: '状态', align: 'center'},
			{field: 'option_id', sort: true, title: '操作ID', align: 'center'},
			{field: 'remark', sort: true, title: '商品ID', align: 'center'},
			{field: 'create_time', sort: true, title: '创建时间', align: 'center'},
			{field: 'update_time', sort: true, title: '修改时间', align: 'center'},
			{align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200, align: 'center'}
		]];
	};
	
	Picture.search = function (){
		var queryData = {};
		queryData['imgUrl'] = $("#imgUrl").val();
		table.reload(Picture.tableId, {where: queryData});
	}
	
	Picture.openAddPicture = function(){
		admin.putTempData('formOk', false);
		top.layui.admin.open({
            type: 2,
            title: '添加轮播',
            content: Feng.ctxPath + '/wedding/app_picture/picture_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Picture.tableId);
            }
        });
	} 
	
	var tableResult = table.render({
		elem: '#' + Picture.tableId,
		url: Feng.ctxPath + '/wedding/app_picture/list',
		page: true,
		height: "full-158",
	    cellMinWidth: 100,
	    cols: Picture.initColumn()
	});
	
	Picture.onDeletePicture = function (data){
		var operation = function (){
			var ajax = new $ax(Feng.ctxPath + "/wedding/app_picture/remove", function (){
				Feng.success("删除成功!");
				table.reload(Picture.tableId);
			}, function (data){
				Feng.error("删除失败!" + data.responseJSON.message + "!");
			});
			ajax.set("id", data.id);
			ajax.start();
		};
		Feng.confirm("是否删除图片?", operation);
	}
	
	Picture.onEditPicture = function (data){
		admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改角色',
            content: Feng.ctxPath + '/wedding/app_picture/picture_edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Picture.tableId);
            }
        });
	}
	
	//搜索按钮点击事件
	$('#btnSearch').click(function (){
		Picture.search();
	});
	
	// 添加按钮点击事件
    $('#btnAdd').click(function () {
    	Picture.openAddPicture();
    });
	
	//工具条点击事件
	table.on('tool(' + Picture.tableId + ')', function(obj){
		var data = obj.data;
		var layEvent = obj.event;
	
		if(layEvent === 'edit'){
			Picture.onEditPicture(data);
		} else if (layEvent === 'delete'){
			Picture.onDeletePicture(data);
		} else if (layEvent === 'show'){
			
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



