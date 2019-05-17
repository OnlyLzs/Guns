package cn.stylefeng.guns.modular.wedding.service;

import cn.stylefeng.guns.modular.wedding.entity.App_product;

import java.io.IOException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * app商品表： 婚礼 旅拍  服务类
 * </p>
 *
 * @author warren
 * @since 2019-04-12
 */
public interface IApp_productService extends IService<App_product> {

	/**
	 * @Description 查询商品(分页)
	 * @author Jason
	 * @date 2019年5月14日
	 * @param condition
	 * @return
	 */
	Page<Map<String, Object>> selectProducts(String condition);

	/**
	 * @Description 添加商品
	 * @author Jason
	 * @date 2019年5月14日
	 * @param product
	 * @param productPictures 
	 * @return
	 * @throws IOException 
	 */
	boolean addProduct(@Valid App_product product, MultipartFile[] productPictures) throws IOException;

	/**
	 * @Description 设置商品状态
	 * @author Jason
	 * @date 2019年5月14日
	 * @param productId
	 * @param status
	 */
	void setStatus(Long productId, Boolean status);

	/**
	 * @Description 将商品置为推荐
	 * @author Jason
	 * @date 2019年5月15日
	 * @param productId
	 */
	Boolean setRecommend(Long productId);
	
	/** 
	 * @Description 获取商品的信息
	 * @author Jason
	 * @date 2019年5月17日
	 * @param productId
	 * @return
	 */
	App_product getProductInfo(Long productId);

	/**
	 * @Description 修改商品信息
	 * @author Jason
	 * @date 2019年5月17日
	 * @param product
	 * @param productPictures
	 * @return
	 */
	boolean editProduct(@Valid App_product product, MultipartFile[] productPictures) throws IOException;

}
