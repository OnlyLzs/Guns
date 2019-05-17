package cn.stylefeng.guns.modular.wedding.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.constant.dictmap.UserDict;
import cn.stylefeng.guns.core.common.constant.state.ManagerStatus;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.system.model.UserDto;
import cn.stylefeng.guns.modular.system.warpper.App_storeWrapper;
import cn.stylefeng.guns.modular.system.warpper.UserWrapper;
import cn.stylefeng.guns.modular.wedding.entity.App_product;
import cn.stylefeng.guns.modular.wedding.entity.App_store;
import cn.stylefeng.guns.modular.wedding.service.IApp_productService;
import cn.stylefeng.guns.modular.wedding.service.IApp_storeService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 * <p>
 * app商品表： 婚礼 旅拍  前端控制器
 * </p>
 *
 * @author warren
 * @since 2019-04-12
 */
@Controller
@RequestMapping("/wedding/app_product")
public class App_productController extends BaseController {
	
	private String PREFIX = "/modular/wedding/product/";
	
	@Autowired
	private IApp_productService productService;
	@Autowired
	private IApp_storeService storeService;
	
	/**
	 * @Description 商品页面跳转
	 * @author Jason
	 * @date 2019年5月14日
	 * @return
	 */
    @RequestMapping("")
    public String index() {
        return PREFIX + "product.html";
    }
    
    /**
     * @Description 添加页面跳转
     * @author Jason
     * @date 2019年5月17日
     * @param model
     * @return
     */
    @RequestMapping("/product_add")
    public String addView(Model model) {
    	List<App_store> storeList = storeService.list();
    	model.addAttribute("storeList", storeList);
        return PREFIX + "product_add.html";
    }
    
   /**
    * @Description 修改页面跳转
    * @author Jason
    * @date 2019年5月17日
    * @param model
    * @return
    */
    @RequestMapping("/product_edit")
    public String editView(Model model) {
    	List<App_store> storeList = storeService.list();
    	model.addAttribute("storeList", storeList);
        return PREFIX + "product_edit.html";
    }
    
    
    /**
     * @Description 获取商品列表
     * @author Jason
     * @date 2019年5月14日
     * @param name
     * @return
     */
    @Permission
    @ResponseBody
    @RequestMapping(value = "/list")
    public Object list(@RequestParam(value = "condition", required = false) String condition) {
    	Page<Map<String, Object>> products = productService.selectProducts(condition);
        return LayuiPageFactory.createPageInfo(products);
    }
    
    /**
     * @Description 添加商品
     * @author Jason
     * @date 2019年5月17日
     * @param product
     * @param productPictures
     * @param bindingResult
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResponseData add(@Valid App_product product, @RequestParam("file")MultipartFile[] productPictures, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        //server.connectionTimeout =180000
        try {
			boolean result = this.productService.addProduct(product, productPictures);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return SUCCESS_TIP;
    }
    
    /**
     * @Description 修改商品
     * @author Jason
     * @date 2019年5月17日
     * @param product
     * @param productPictures
     * @param bindingResult
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public ResponseData edit(@Valid App_product product, @RequestParam("file")MultipartFile[] productPictures, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
			boolean result = this.productService.editProduct(product, productPictures);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return SUCCESS_TIP;
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
    public ResponseData setStatus(@RequestParam Long productId, @RequestParam(defaultValue="false")boolean status) {
        if (ToolUtil.isEmpty(productId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.productService.setStatus(productId, status);
        return SUCCESS_TIP;
    }
    
    
    /**
     * @Description 删除商品
     * @author Jason
     * @date 2019年5月14日
     * @param userId
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData deleteProduct(Long productId) {

        this.productService.removeById(productId);
        return SUCCESS_TIP;
    }
    
    /**
     * @Description 置为推荐
     * @author Jason
     * @date 2019年5月14日
     * @param userId
     */
    @RequestMapping("/recommend")
    @ResponseBody
    public ResponseData recommendProduct(Long productId) {

        boolean result = this.productService.setRecommend(productId);
        if(result) {
        	return SUCCESS_TIP;
        }
        return ResponseData.error("推荐失败哦");
    }
    
    /**
     * @Description 获取商品信息
     * @author Jason
     * @date 2019年5月17日
     * @param productId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getProductInfo")
    public ResponseData getProductInfo(Long productId) {
       App_product product = this.productService.getProductInfo(productId);
       Map<String, Object> productInfo = BeanUtil.beanToMap(product);
       return ResponseData.success(productInfo);
    }
    

}

