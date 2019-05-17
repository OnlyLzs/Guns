package cn.stylefeng.guns.modular.wedding.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.wedding.entity.App_order;
import cn.stylefeng.guns.modular.wedding.service.IApp_orderService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * <p>
 * app 订单表 前端控制器
 * </p>
 *
 * @author Jason
 * @since 2019-05-16
 */
@Controller
@RequestMapping("/wedding/app_order")
public class App_orderController extends BaseController {
	
	private String PREFIX = "/modular/wedding/order/";
	
	@Autowired
	IApp_orderService orderService;
	
	/**
	 * @Description 订单页面跳转
	 * @author Jason
	 * @date 2019年5月14日
	 * @return
	 */
    @RequestMapping("")
    public String index() {
        return PREFIX + "order.html";
    }
    
    /**
	 * @Description 订单页面跳转
	 * @author Jason
	 * @date 2019年5月14日
	 * @return
	 */
    @RequestMapping("/drawback")
    public String drawBackIndex() {
        return PREFIX + "order_drawback.html";
    }
    
    /**
     * @Description 获取商品列表
     * @author Jason
     * @date 2019年5月14日
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list")
    public Object list(@RequestParam(value = "condition", required = false) String condition, 
    		@RequestParam(value = "timeLimit", required = false) String timeLimit, 
    		@RequestParam(value = "orderStatus", required = false) String orderStatus) {
    	Page<Map<String, Object>> orders = orderService.selectOrders(condition, timeLimit, orderStatus);
        return LayuiPageFactory.createPageInfo(orders);
    }
    
    /**
     * @Description 获取退款商品列表
     * @author Jason
     * @date 2019年5月14日
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list/drawback")
    public Object listDrawBack(@RequestParam(value = "condition", required = false) String condition, 
    		@RequestParam(value = "timeLimit", required = false) String timeLimit, 
    		@RequestParam(value = "orderStatus", required = false) String orderStatus) {
    	Page<Map<String, Object>> orders = orderService.selectDrawBackOrders(condition, timeLimit, orderStatus);
        return LayuiPageFactory.createPageInfo(orders);
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
    public ResponseData setStatus(@RequestParam Long orderId, @RequestParam(defaultValue="false")String status) {
        if (ToolUtil.isEmpty(orderId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.orderService.setStatus(orderId, status);
        return SUCCESS_TIP;
    }
    
   
    @RequestMapping("/detail/{orderId}")
    public String showDetailIndex(@PathVariable Long orderId,Model model) {
    	
    	App_order order = this.orderService.showDetail(orderId);
    	model.addAttribute("order", order);
        return PREFIX + "order_detail.html";
    }
    
    
    
    @RequestMapping("/detail/show/{orderId}")
    @ResponseBody
    public LayuiPageInfo showDetail(@PathVariable Long orderId) {
        if (ToolUtil.isEmpty(orderId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        Page<Map<String, Object>> passengers = orderService.selectPassengers(orderId);
        return LayuiPageFactory.createPageInfo(passengers);
    }
    
    

}

