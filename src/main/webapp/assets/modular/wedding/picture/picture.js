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
			roleName : ""
		}
	};
	
	Picture.initColumn = function () {
		return [[
			{type: 'checkbox'},
			{field: 'id', hide: true, sort: true, title: '图片id'},
			{field: 'img_url', sort: true, title: '名称'},
			{field: 'link_url', sort: true, title: '链接地址'},
			{field: 'option_id', sort: true, title: '店家id'},
			{align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
		]];
	};
	
	Picture.search = function (){
		var queryDate = {};
		queryData['optionId'] = $("#optionId").val();
		table.reload(Picture.tableId, {where: queryData});
	}
	
	var tableResult = table.render({
		elem: '#' + Picture.tableId,
		url: Feng.ctxPath + '/picture/list',
		page: true,
		height: "full-158",
	    cellMinWidth: 100,
	    cols: Picture.initColumn()
	});
	
});