package cn.stylefeng.guns.modular.wedding.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.system.warpper.DeptWrapper;
import cn.stylefeng.guns.modular.wedding.service.impl.App_areaServiceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
public class App_pictureController {
	
	private String PREFIX = "/modular/wedding/app_picture/";

    @Autowired
    private App_areaServiceImpl app_areaServiceImpl;

    /**
     * 跳转到部门管理首页
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
    public Object list(@RequestParam(value = "condition", required = false) String condition,
                       @RequestParam(value = "deptId", required = false) String deptId) {
     
        return null;
    }
}

