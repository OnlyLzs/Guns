package cn.stylefeng.guns.modular.wedding.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.constant.dictmap.DeleteDict;
import cn.stylefeng.guns.core.common.constant.dictmap.MenuDict;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.core.util.ConfigProperties;
import cn.stylefeng.guns.core.util.ImageUtil;
import cn.stylefeng.guns.modular.system.warpper.App_areaWrapper;
import cn.stylefeng.guns.modular.wedding.entity.App_area;
import cn.stylefeng.guns.modular.wedding.service.IApp_areaService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * app 地区表 前端控制器
 * </p>
 *
 * @author warren
 * @since 2019-04-12
 */
@Controller
@RequestMapping("/wedding/app_area")
public class App_areaController extends BaseController{

	private String PREFIX = "/modular/wedding/area/";
	
	@Autowired
	private IApp_areaService iApp_areaService;
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
        return PREFIX + "area.html";
    }
    
    /**
     * 跳转到菜单列表列表页面
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/area_add")
    public String menuAdd() {
        return PREFIX + "area_add.html";
    }
    
    /**
     * 跳转到菜单详情列表页面
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/area_edit")
    public String menuEdit(@RequestParam Long id) {
        if (ToolUtil.isEmpty(id)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        return PREFIX + "area_edit.html";
    }
    
    /**
     * 获取列表（s树形）
     *
     * @author fengshuonan
     * @Date 2019年2月23日22:01:47
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/listTree")
    @ResponseBody
    public Object listTree(@RequestParam(required = false) String name,
                           @RequestParam(required = false) String level) {
        List<Map<String, Object>> area = this.iApp_areaService.selectMenuTree(name, level);
       
        
        LayuiPageInfo result = new LayuiPageInfo();
        result.setData(new App_areaWrapper(area).wrap());
        return result;
    }
    
    /**
     * 获取菜单列表(选择父级菜单用)
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:54 PM
     */
    @RequestMapping(value = "/selectMenuTreeList")
    @ResponseBody
    public List<ZTreeNode> selectMenuTreeList() {
        List<ZTreeNode> roleTreeList = this.iApp_areaService.menuTreeList();
        roleTreeList.add(ZTreeNode.createParent());
        return roleTreeList;
    }
    
    /**
     * 删除
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/remove")
    @BussinessLog(value = "删除菜单", key = "menuId", dict = DeleteDict.class)
    @ResponseBody
    public ResponseData remove(@RequestParam Long id) {
        if (ToolUtil.isEmpty(id)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
    
        this.iApp_areaService.delMenuContainSubMenus(id, configProperties.getPictureDisk());
        return SUCCESS_TIP;
    }
    
    /**
     * 新增菜单
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/add")
    @BussinessLog(value = "菜单新增", key = "name", dict = MenuDict.class)
    @ResponseBody
    public ResponseData add(MultipartFile file,App_area app_area) throws Exception{
		String img_url = ImageUtil.saveToDiskFromMultipartFile(file, configProperties.getPictureDisk());
		app_area.setCreate_time(new SimpleDateFormat().format(new Date()));
		app_area.setUpdate_time(new SimpleDateFormat().format(new Date()));
		app_area.setImg_url(img_url);
		if(app_area.getParent_id() == 0) {
			app_area.setLevel("1");
		}else {
			App_area parent = this.iApp_areaService.getById(app_area.getParent_id());
			int level = (Integer.valueOf(parent.getLevel()) + 1);
			app_area.setLevel(String.valueOf(level));
		}
		this.iApp_areaService.save(app_area);
        return SUCCESS_TIP;
    }
    
    /**
     * 获取菜单信息
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/getMenuInfo")
    @ResponseBody
    public ResponseData getMenuInfo(@RequestParam Long id) {
        if (ToolUtil.isEmpty(id)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        App_area app_area = this.iApp_areaService.getById(id);
        Map<String, Object> area = BeanUtil.beanToMap(app_area);
        
        return ResponseData.success(new App_areaWrapper(area).wrap());
    }
    
    /**
     * 修该菜单
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/edit")
    @BussinessLog(value = "修改菜单", key = "name", dict = MenuDict.class)
    @ResponseBody
    public ResponseData edit(MultipartFile file, App_area area) throws Exception{
    	
    	if(file!=null && !file.isEmpty()) {
    		App_area oldArea = this.iApp_areaService.getById(area.getId());
    		File oldPicture = new File(configProperties.getPictureDisk() + oldArea.getImg_url());
    		if(oldPicture.exists()) {
    			oldPicture.delete();
    		}
    		
    		String img_url = ImageUtil.saveToDiskFromMultipartFile(file, configProperties.getPictureDisk());
    		area.setImg_url(img_url);
    	}
    	this.iApp_areaService.updateById(area);
    	
        return SUCCESS_TIP;
    }
}

