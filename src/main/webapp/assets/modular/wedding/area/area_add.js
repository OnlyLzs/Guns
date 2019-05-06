/**
 * 详情对话框
 */
var AreaInfoDlg = {
    data: {
        id: "",
        parent_id: ""
    }
};

layui.use(['layer', 'form', 'admin', 'laydate', 'ax', 'upload'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;
    var layer = layui.layer;
    var upload = layui.upload;
    
    //让当前iframe弹层高度适应
    admin.iframeAuto();
    
    upload.render({
    	elem: '#selectPicture',
    	auto: false,
    	accept: 'images',
    	choose: function(obj){
    		obj.preview(function(index, file, result){
    	        $('#picture').attr('src', result); //图片链接（base64）
    	    });
    	},
    	before: function(obj){
    	},
    	done: function(res){
    	}
    });
    
    //点击父级菜单
    $('#parent_id').click(function () {
    	var formName = encodeURIComponent("parent.AreaInfoDlg.data.id");
        var formId = encodeURIComponent("parent.AreaInfoDlg.data.parent_id");
        
        //ƒ encodeURIComponent() { [native code] } js原生方法
        //alert(formName);
        //alert(formId);
        var treeUrl = encodeURIComponent("/wedding/app_area/selectMenuTreeList");
        
        layer.open({
            type: 2,
            title: '父级菜单',
            area: ['300px', '400px'],
            content: Feng.ctxPath + '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl,
            end: function () {
                $("#id").val(AreaInfoDlg.data.id);
                $("#parent_id").val(AreaInfoDlg.data.parent_id);
            }
        });
    });
    
    //表单提交事假
    form.on('submit(btnSubmit)', function (data){
    	var areaForm = $("#areaForm")[0];
    	var formData = new FormData(areaForm);
		$.ajax({
		    url: Feng.ctxPath + "/wedding/app_area/add",
		    type: "POST",
		    data: formData,
		    async: false,
		    contentType: false,
		    processData: false,
		    error: function () {
		    	Feng.error("添加失败！");
		    },
		    success: function (data) {
		    	Feng.success("添加成功！");

	            //传给上个页面，刷新table用
	            admin.putTempData('formOk', true);

	            //关掉对话框
	            admin.closeThisDialog();
		    }
		});
	});
});