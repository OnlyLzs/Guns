/**
 * 用户详情对话框
 */
var UserInfoDlg = {
    data: {
        deptId: "",
        deptName: ""
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

    // s让当前iframe弹层高度适应
    //admin.iframeAuto();

    //s获取商品信息信息
    var ajax = new $ax(Feng.ctxPath + "/wedding/app_product/getProductInfo?productId=" + Feng.getUrlParam("productId"));
    var result = ajax.start();
    console.log(result.data)
    // s表单数据绑定
    form.val('productForm', result.data);
    $('#target_city').val(result.data.area.id);
    $('#target_city_name').val(result.data.area.name);
    for ( var i = 0; i <result.data.pictureList.length; i++){
        $('#productPicturesPreview').append('<img src='+result.data.pictureList[i].img_url+'/>');
    }
    UE.getEditor('editor').setContent('');
    UE.getEditor('editor').execCommand('insertHtml', result.data.product_detail_text);
    
    
  //s多图片上传
    upload.render({
      elem: '#productPictures'
      ,auto: false
      ,multiple: true
      ,choose: function(obj){
    	  //s清空预览框
    	  document.getElementById('productPicturesPreview').innerHTML = "";
    	//s预读本地文件示例，不支持ie8
          obj.preview(function(index, file, result){
            console.log(index+"--"+file+"--"+result);
            $('#productPicturesPreview').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
          });
      }
      ,before: function(obj){
       
      }
      ,done: function(res){
        //上传完毕
      }
    });
    
    
    // s点击城市时
    $('#target_city_name').click(function () {
        var formName = encodeURIComponent("parent.AreaInfoDlg.data.targetCityName");
        var formId = encodeURIComponent("parent.AreaInfoDlg.data.targetCityId");
        var treeUrl = encodeURIComponent("/wedding/app_area/selectMenuTreeList");

        layer.open({
            type: 2,
            title: '城市选择',
            area: ['300px', '700px'],
            content: Feng.ctxPath + '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl,
            end: function () {
                console.log(AreaInfoDlg.data);
                $("#target_city").val(AreaInfoDlg.data.targetCityId);
                $("#target_city_name").val(AreaInfoDlg.data.targetCityName);
            }
        });
    });
//    
//    // s点击部门时
//    $('#deptName').click(function () {
//        var formName = encodeURIComponent("parent.UserInfoDlg.data.deptName");
//        var formId = encodeURIComponent("parent.UserInfoDlg.data.deptId");
//        var treeUrl = encodeURIComponent(Feng.ctxPath + "/dept/tree");
//
//        layer.open({
//            type: 2,
//            title: '部门选择',
//            area: ['300px', '400px'],
//            content: Feng.ctxPath + '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl,
//            end: function () {
//                console.log(UserInfoDlg.data);
//                $("#deptId").val(UserInfoDlg.data.deptId);
//                $("#deptName").val(UserInfoDlg.data.deptName);
//            }
//        });
//    });

    // s添加表单验证方法
    form.verify({
        psw: [/^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格'],
        repsw: function (value) {
            if (value !== $('#userForm input[name=password]').val()) {
                return '两次密码输入不一致';
            }
        }
    });

    // s渲染时间选择框
    laydate.render({
        elem: '#birthday'
    });

    // s表单提交事件
    form.on('submit(btnSubmit)', function (data) {
//    	data.field.productHtml = UE.getEditor('editor').getContent();
    	console.log('product表单提交内容：'+JSON.stringify(data))
    	var formData = new FormData(document.getElementById("productForm"));//s表单id
       	 $.ajax({
            type: "POST",//s方法类型
            dataType: "json",//s预期服务器返回的数据类型
            async: false,
            url: Feng.ctxPath + "/wedding/app_product/edit" ,//url
            data: formData,
            contentType: false, //s必须
            processData: false, //s必须
            error: function (response){
    			Feng.error("修改失败！");
    			 //s关掉对话框
	            admin.closeThisDialog();
    		},
    		success: function (response){
    			if(response.code === 200){
    				Feng.success("修改成功！");
    	            //s传给上个页面，刷新table用
    	            admin.putTempData('formOk', true);
    	            //s关掉对话框
    	            admin.closeThisDialog();
    			}else{
    				Feng.error("修改失败！" + data.responseJSON.message);
    				 //s关掉对话框
    	            admin.closeThisDialog();
    			}	
    		}
        });
    });
});