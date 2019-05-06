layui.use(['layer', 'form', 'admin', 'ax', 'upload'], function () {
	var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
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
    			$('#picture').attr('src', result);
    		});
    	},
    	before: function(obj){
    	},
    	done: function(res){
    		
    	}
    });
    
    form.on('submit(btnSubmit)', function (data){
    	var pictureForm = $("#pictureForm")[0];
    	var formData = new FormData(pictureForm);
    	$.ajax({
    		url: Feng.ctxPath + "/wedding/app_picture/add",
    		type: "POST",
    		data: formData,
    		async: false,
    		contentType: false,
 		    processData: false,
    		dataType: 'json',
    		error: function (response){
    			Feng.error("添加失败！");
    		},
    		success: function (response){
    			
    			if(response.code === 200){
    				Feng.success("添加成功！");

    	            //传给上个页面，刷新table用
    	            admin.putTempData('formOk', true);

    	            //关掉对话框
    	            admin.closeThisDialog();
    			}else{
    				Feng.error("添加失败！" + data.responseJSON.message);
    			}	
    	
    		}
    	});
    });
});