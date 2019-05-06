var picUpdateFlag = false;
layui.use(['layer', 'form', 'admin', 'ax', 'upload', 'laydate'], function () {
	var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;
    var upload = layui.upload;
    var laydate = layui.laydate;

    //让当前iframe弹层高度适应
    admin.iframeAuto();
    
    laydate.render({
    	elem: '#open_time',
        type: 'time',
        format: 'H点m分s秒',
        done: function (value, date){
        	
        }
    });
    
    laydate.render({
    	elem: '#close_time',
        type: 'time',
        format: 'H点m分s秒',
        done: function (value, date){
  
        }
    });
    
    upload.render({
    	elem: '#selectPicture',
    	auto: false,
    	accept: 'images',
    	choose: function (obj){
    		obj.preview(function(index, file, result){
    			$('#picture').attr('src', result);
    		});
    		picUpdateFlag = true;
    	},
    	before: function (obj){
    	},
    	done: function (obj){
    	}
    });
    
    //初始化角色的详情数据
    var ajax = new $ax(Feng.ctxPath + "/wedding/app_store/view/" + Feng.getUrlParam("id"));
    
    var result = ajax.start();
    form.val('storeForm',result.data);
    $("#picture").attr("src", result.data.img_url);
    
    form.on('submit(btnSubmit)', function (data){
    	var storeForm = $("#storeForm")[0];
    	var formData = new FormData(storeForm);
    	formData.append("picUpdateFlag", picUpdateFlag);
    	formData.append("img_url", result.data.img_url);
    	$.ajax({
    		url: Feng.ctxPath + "/wedding/app_store/edit",
    		type: "POST",
    		data: formData,
    		async: false,
    		contentType: false,
 		    processData: false,
    		dataType: 'json',
    		error: function (response){
    			Feng.error("修改失败！");
    		},
    		success: function (response){
    			
    			if(response.code === 200){
    				Feng.success("添加成功！");

    	            //传给上个页面，刷新table用
    	            admin.putTempData('formOk', true);

    	            //关掉对话框
    	            admin.closeThisDialog();
    			}else{
    				Feng.error("修改失败！" + data.responseJSON.message);
    			}	
    	
    		}
    	});
    });
});