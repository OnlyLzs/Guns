package cn.stylefeng.guns.modular.wedding.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.util.PasswordUtils;
import cn.stylefeng.guns.modular.wedding.entity.App_user;
import cn.stylefeng.guns.modular.wedding.service.IApp_userService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * app用户表 前端控制器
 * </p>
 *
 * @author Jason
 * @since 2019-05-15
 */
@Controller
@RequestMapping("/wedding/app_user")
public class App_userController extends BaseController {
	
	private String PREFIX = "/modular/wedding/user/";
	
	private static final String definedPwd = "111111";
	
	@Autowired
	IApp_userService userService;
	
	
	
	/**
	 * @Description 移动用户页面跳转
	 * @author Jason
	 * @date 2019年5月14日
	 * @return
	 */
    @RequestMapping("")
    public String index() {
        return PREFIX + "user.html";
    }
    
    /**
     * @Description 获取用户列表
     * @author Jason
     * @date 2019年5月14日
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list")
    public Object list(@RequestParam(value = "condition", required = false) String condition, @RequestParam(required = false) String timeLimit) {
    	Page<Map<String, Object>> users = userService.selectUsers(condition);
        return LayuiPageFactory.createPageInfo(users);
    }
    
    
    /**
     * @Description 设置商品状态
     * @author Jason
     * @date 2019年5月14日
     * @param userId
     * @return
     */
    @RequestMapping("/status")
    @ResponseBody
    public ResponseData setStatus(@RequestParam Long userId, @RequestParam(defaultValue="false")boolean status) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.userService.setStatus(userId, status);
        return SUCCESS_TIP;
    }
    
    
    /**
     * @Description 删除用户
     * @author Jason
     * @date 2019年5月14日
     * @param userId
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData deleteUser(Long userId) {

        this.userService.removeById(userId);
        return SUCCESS_TIP;
    }
    
    /**
     * @Description 重置密码
     * @author Jason
     * @date 2019年5月14日
     * @param userId
     */
    @RequestMapping("/reset")
    @ResponseBody
    public ResponseData resetPwd(Long userId) {
    	App_user user = this.userService.getById(userId);
    	user.setLogin_password(PasswordUtils.encodPassword(definedPwd));
    	this.userService.updateById(user);
        return SUCCESS_TIP;
    }
    
   
}

