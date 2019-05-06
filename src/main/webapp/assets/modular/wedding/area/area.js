layui.use(['layer', 'form', 'ztree', 'laydate', 'admin', 'ax', 'table', 'treetable'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var $ZTree = layui.ztree;
    var $ax = layui.ax;
    var laydate = layui.laydate;
    var admin = layui.admin;
    var table = layui.table;
    var treetable = layui.treetable;

	var Area = {
		tableId: "areaTable",
		condition: {
			id: "",
			name : "",
			level: ""
		}
	};
	
	Area.initColumn = function () {
		return [[
			{type: 'numbers'},
			{field: 'id', hide: true, sort: true, title: '编号', align:'center'},
			{field: 'name', sort: true, title: '名称'},
			{field: 'parent_id', sort: true, title: '父编号', align:'center', templet: '#parentIdTemplet'},
			{field: 'level', sort: true, title: '等级', align:'center'},
			{field: 'status', sort: true, title: '状态', align:'center', templet: '#status'},
			{field: 'img_url', sort: true, title: '图片', align:'center', templet: '#imgUrl'},
			{field: 'create_time', sort: true, title: '创建时间', align: 'center'},
			{field: 'update_time', sort: true, title: '修改时间', align: 'center'},
			{align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200, align:'center'}
		]];
	};
	
	
	Area.initTable = function (areaId, data) {
		return treetable.render({
			elem: '#' + areaId,
			url: Feng.ctxPath + '/wedding/app_area/listTree',
			where: data,
			page: false,
			height: "full-158",
			cellMinWidth: 100,
			cols: Area.initColumn(),
			treeColIndex: 2,
			treeSpid: "0",
			treeIdName: 'id',
			treePidName: 'parent_id',
			treeDefaultClose: false,
			treeLinkage: true
		});
	}
	// 渲染表格
	var tableResult = Area.initTable(Area.tableId);
	
	Area.search = function () {
		var queryData = {};
		queryData['name'] = $("#name").val();
		queryData['level'] = $('#level').val();
		Area.initTable(Area.tableId, queryData);
	}
	
	Area.openAddArea = function (){
		admin.putTempData('forkOk', false);
		top.layui.admin.open({
			type: 2,
			title: '添加目的地',
			content: Feng.ctxPath + '/wedding/app_area/area_add',
			end: function (){
				 admin.getTempData('formOk') && Area.initTable(Area.tableId);
			}
		});
	}
	
	Area.onEditArea = function (data){
		admin.putTempData('formOk', false);
		top.layui.admin.open({
			type: 2,
			title: '编辑地区',
			content: Feng.ctxPath + "/wedding/app_area/area_edit?id=" + data.id,
			end: function (){
				 admin.getTempData('formOk') && Area.initTable(Area.tableId);
			}
		});
	}
	
	Area.onDeleteArea = function (data){
		var operation = function () {
			var ajax = new $ax(Feng.ctxPath + "/wedding/app_area/remove", function () {
				Feng.success("删除成功!");
				Area.condition.id = "";
				Area.initTable(Area.tableId);
			}, function (data){
				Feng.error("删除失败!" + data.responseJSON.message + "!");
			});
			ajax.set("id", data.id);
			ajax.start();
		};
		Feng.confirm("是否删除地址" + data.name + "?", operation);
	}
	
	//添加按钮点击事件
	$('#btnAdd').click(function (){
		Area.openAddArea();
	});
	
	$('#btnSearch').click(function () {
		Area.search();
	});  
	
	//工具条点击事件
	table.on('tool(' + Area.tableId + ')', function (obj){
		var data = obj.data;
		var layEvent = obj.event;
		
		if(layEvent === 'edit'){
			Area.onEditArea(data);
		}else if(layEvent === 'delete'){
			Area.onDeleteArea(data);
		}
	});
	
});

function showPicture(dom){
	var picture = $(dom).find("img");
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
    
}