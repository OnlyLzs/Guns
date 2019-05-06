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
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.util.ConfigProperties;
import cn.stylefeng.guns.core.util.ImageUtil;
import cn.stylefeng.guns.modular.system.warpper.App_pictureWrapper;
import cn.stylefeng.guns.modular.system.warpper.App_storeWrapper;
import cn.stylefeng.guns.modular.wedding.entity.App_picture;
import cn.stylefeng.guns.modular.wedding.entity.App_store;
import cn.stylefeng.guns.modular.wedding.service.IApp_storeService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * app店家表 前端控制器
 * </p>
 *
 * @author warren
 * @since 2019-04-12
 */
@Controller
@RequestMapping("/wedding/app_store")
public class App_storeController extends BaseController{
	
	private String PREFIX = "/modular/wedding/store/";
	
	@Autowired
    private IApp_storeService iApp_storeService;
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
        return PREFIX + "store.html";
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
    public Object list(@RequestParam(value = "name", required = false) String name) {
    	Page<Map<String, Object>> stores = this.iApp_storeService.selectStores(name);
        Page<Map<String, Object>> wrap = new App_storeWrapper(stores).wrap();  	
    	return LayuiPageFactory.createPageInfo(wrap);
    }
    
    /**
     * 跳转到添加轮播图
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:30 PM
     */
    @RequestMapping(value = "/store_add")
    public String roleAdd() {
        return PREFIX + "store_add.html";
    }
    
    
    /**
     * 跳转到修改角色
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @Permission
    @RequestMapping(value = "/store_edit")
    public String roleEdit(@RequestParam Long id) {
        if (ToolUtil.isEmpty(id)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        return PREFIX + "/store_edit.html";
    }
    
    /**
     *增加轮播图
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public ResponseData add(MultipartFile file, App_store app_store) throws Exception{
    	String img_url = ImageUtil.saveToDiskFromMultipartFile(file, configProperties.getPictureDisk());
    	app_store.setCreate_time(new SimpleDateFormat().format(new Date()));
    	app_store.setUpdate_time(new SimpleDateFormat().format(new Date()));
    	app_store.setImg_url(img_url);
    	this.iApp_storeService.save(app_store);
        return SUCCESS_TIP;
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
    	
    	App_store store = this.iApp_storeService.getById(id);
    	if(store!=null) {
    		File file = new File(configProperties.getPictureDisk() + store.getImg_url());
    		if(file.exists()) {
    			file.delete();
    		}
    	}
    	
    	this.iApp_storeService.removeById(id);
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
        App_store store = this.iApp_storeService.getById(id);
        Map<String, Object> roleMap = BeanUtil.beanToMap(store);
        return ResponseData.success(new App_storeWrapper(roleMap).wrap());
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
    public ResponseData edit(@RequestParam(required=false,name="file") MultipartFile file, App_store app_store, Boolean picUpdateFlag) throws Exception{
    	String oldImg_url = app_store.getImg_url();
		int index = oldImg_url.lastIndexOf("/") + 1;
		String img_name = app_store.getImg_url().substring(index, oldImg_url.length());
		
    	if(picUpdateFlag) {
    		File oldFile = new File(configProperties.getPictureDisk() + img_name);
    		if(oldFile.exists()) {
    			oldFile.delete();
    		}
    		String img_url = ImageUtil.saveToDiskFromMultipartFile(file, configProperties.getPictureDisk());
    		app_store.setImg_url(img_url);
    	}else {
    		app_store.setImg_url(img_name);
    	}
    	
    	app_store.setUpdate_time(new SimpleDateFormat().format(new Date()));
    	this.iApp_storeService.updateById(app_store);
        return SUCCESS_TIP;
    }
}

