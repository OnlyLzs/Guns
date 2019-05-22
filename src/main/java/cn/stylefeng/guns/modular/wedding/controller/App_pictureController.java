package cn.stylefeng.guns.modular.wedding.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.constant.dictmap.DeleteDict;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.util.ConfigProperties;
import cn.stylefeng.guns.core.util.ImageUtil;
import cn.stylefeng.guns.modular.system.warpper.App_pictureWrapper;
import cn.stylefeng.guns.modular.wedding.entity.App_picture;
import cn.stylefeng.guns.modular.wedding.service.IApp_pictureService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * app 轮播图表 前端控制器
 * </p>
 *
 * @author warren
 * @since 2019-04-12
 */
@Controller
@RequestMapping("/wedding/app_picture")
public class App_pictureController extends BaseController{
	
	
	private String PREFIX = "/modular/wedding/picture/";

    @Autowired
    private IApp_pictureService iApp_pictureService;
    @Autowired
	private ConfigProperties configProperties;
    
    /**
     * 跳转到轮播图管理首页
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "picture.html";
    }
    
    /**
     * 获取所有轮播图列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/list")
    @Permission
    @ResponseBody
    public Object list(@RequestParam(value = "imgUrl", required = false) String imgUrl) {
    	Page<Map<String, Object>> pictures = this.iApp_pictureService.selectPictures(imgUrl);
        Page<Map<String, Object>> wrap = new App_pictureWrapper(pictures).wrap();  	
    	return LayuiPageFactory.createPageInfo(wrap);
    }
    
    /**
     * 跳转到添加轮播图
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:30 PM
     */
    @RequestMapping(value = "/picture_add")
    public String roleAdd() {
        return PREFIX + "picture_add.html";
    }
    
    /**
     * 删除轮播图
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/remove")
    @BussinessLog(value = "删除轮播图", key = "id", dict = DeleteDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData remove(@RequestParam Long id) {
    	
    	App_picture picture = this.iApp_pictureService.getById(id);
    	if(picture!=null) {
    		File file = new File(configProperties.getPictureLocation() + picture.getImg_url());
    		if(file.exists()) {
    			file.delete();
    		}
    	}
    	
    	this.iApp_pictureService.removeById(id);
        return SUCCESS_TIP;
    }
    
    /**
     * 跳转到修改角色
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @Permission
    @RequestMapping(value = "/picture_edit")
    public String roleEdit(@RequestParam Long id) {
        if (ToolUtil.isEmpty(id)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        return PREFIX + "/picture_edit.html";
    }
    
    
    /**
     *增加轮播图
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public ResponseData add(MultipartFile file, App_picture app_picture) throws Exception{
    	String img_url = ImageUtil.saveToDiskFromMultipartFile(file, configProperties.getPictureLocation());
    	app_picture.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    	app_picture.setImg_url(img_url);
    	app_picture.setOption_id(ShiroKit.getUser().getId().intValue());
    	this.iApp_pictureService.save(app_picture);
        return SUCCESS_TIP;
    }

    
    /**
     * 轮播图修改
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/edit")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData edit(@RequestParam(required=false,name="file") MultipartFile file, App_picture app_picture, Boolean picUpdateFlag) throws Exception{
    	if(picUpdateFlag) {
    		File oldFile = new File(configProperties.getPictureLocation() + app_picture.getImg_url());
    		if(oldFile.exists()) {
    			oldFile.delete();
    		}
    		String img_url = ImageUtil.saveToDiskFromMultipartFile(file, configProperties.getPictureLocation());
    		app_picture.setImg_url(img_url);
    		app_picture.setOption_id(ShiroKit.getUser().getId().intValue());
    		app_picture.setUpdate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    	}else {
    		app_picture.setOption_id(ShiroKit.getUser().getId().intValue());
    		app_picture.setUpdate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    	}
    	this.iApp_pictureService.updateById(app_picture);
        return SUCCESS_TIP;
    }
    
    /**
     * 查看轮播图
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/view/{id}")
    @ResponseBody
    public ResponseData view(@PathVariable Long id) {
        if (ToolUtil.isEmpty(id)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        App_picture app_picture = this.iApp_pictureService.getById(id);
        Map<String, Object> roleMap = BeanUtil.beanToMap(app_picture);
        return ResponseData.success(new App_pictureWrapper(roleMap).wrap());
    }
}

