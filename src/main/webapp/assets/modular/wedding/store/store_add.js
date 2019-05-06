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
    
    var openDate;
    var closeDate;
    
    laydate.render({
    	elem: '#open_time',
        type: 'time',
        format: 'H点m分s秒',
        done: function (value, date){
        	openDate = date;
        }
    });
    
    laydate.render({
    	elem: '#close_time',
        type: 'time',
        format: 'H点m分s秒',
        done: function (value, date){
        	if(openDate.hours > date.hours){
        		console.log(6);
        	}else{
        		
        	}
        }
    });
    
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
    	var pictureForm = $("#storeForm")[0];
    	var formData = new FormData(pictureForm);
    	console.log(formData);
    	$.ajax({
    		url: Feng.ctxPath + "/wedding/app_store/add",
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