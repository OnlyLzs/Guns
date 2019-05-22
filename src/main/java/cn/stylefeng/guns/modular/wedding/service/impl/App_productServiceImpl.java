package cn.stylefeng.guns.modular.wedding.service.impl;

import cn.stylefeng.guns.core.common.constant.state.ProductStatus;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.wedding.entity.App_picture;
import cn.stylefeng.guns.modular.wedding.entity.App_product;
import cn.stylefeng.guns.modular.wedding.entity.App_product_picture;
import cn.stylefeng.guns.modular.wedding.mapper.App_productMapper;
import cn.stylefeng.guns.modular.wedding.service.IApp_pictureService;
import cn.stylefeng.guns.modular.wedding.service.IApp_productService;
import cn.stylefeng.guns.modular.wedding.service.IApp_product_pictureService;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * app商品表： 婚礼 旅拍  服务实现类
 * </p>
 *
 * @author warren
 * @since 2019-04-12
 */
@Service
public class App_productServiceImpl extends ServiceImpl<App_productMapper, App_product> implements IApp_productService {

	@Value("${picture.location}")
	private String uploadPath;
	@Value("${picture.server}")
	private String serverUrl;
	
	
	@Autowired
	IApp_product_pictureService product_pictureService;
	
	@Autowired
	IApp_pictureService pictureService;
	
	
	@Override
	public Page<Map<String, Object>> selectProducts(String condition) {
		Page page = LayuiPageFactory.defaultPage();
		return this.baseMapper.selectProducts(page, condition);
	}

	@Override
	@Transactional
	public boolean addProduct(@Valid App_product product, MultipartFile[] productPictures) throws IOException {
		product.setNumb(Math.random()*10000+"");
		int insert = this.baseMapper.insert(product);
		for(MultipartFile picture: productPictures) {
			//s保存图片到本地
			String fileName = System.currentTimeMillis() + picture.getOriginalFilename();
			File saveFile = new File(uploadPath);
			if (!saveFile.exists()) {
				saveFile.mkdirs();
			}
			FileOutputStream out = new FileOutputStream(uploadPath + fileName);
			out.write(picture.getBytes());
			out.flush();
			out.close();
			//s将图片路径存放到数据库
			product_pictureService.save(new App_product_picture(fileName,product.getId()));
		}
		return true;
	}
	
	
	@Override
	@Transactional
	public boolean editProduct(@Valid App_product product, MultipartFile[] productPictures) throws IOException {
		product.setNumb(Math.random()*10000+"");
		int update = this.baseMapper.updateById(product);
		if(productPictures.length>0) {
			//s删除之前商品照片的记录
			QueryWrapper<App_product_picture> query = new QueryWrapper<>();
			query.eq("product_id",product.getId());
			product_pictureService.remove(query);
			for(MultipartFile picture: productPictures) {
				//s保存图片到本地
				String fileName = System.currentTimeMillis() + picture.getOriginalFilename();
				File saveFile = new File(uploadPath);
				if (!saveFile.exists()) {
					saveFile.mkdirs();
				}
				FileOutputStream out = new FileOutputStream(uploadPath + fileName);
				out.write(picture.getBytes());
				out.flush();
				out.close();
				//s将图片路径存放到数据库
				product_pictureService.save(new App_product_picture(fileName,product.getId()));
			}
		}
		return true;
	}
	

	@Override
	public void setStatus(Long productId, Boolean status) {
		if(status) {
			int result = this.baseMapper.setStatus(productId, ProductStatus.OK.getCode());
		}else {
			int result = this.baseMapper.setStatus(productId, ProductStatus.FREEZED.getCode());
		}
	}

	@Override
	public Boolean setRecommend(Long productId) {
		QueryWrapper<App_product_picture> query = new QueryWrapper<App_product_picture>();
		query.eq("product_id", productId);
		List<App_product_picture> list = product_pictureService.list(query);
		if(!list.isEmpty()) {
			App_picture app_picture = new App_picture();
			app_picture.setImg_url(list.get(0).getImg_url());
			app_picture.setLink_url(productId.toString());
			pictureService.save(app_picture);
			return true;
		}
		return false;
		
	}

	@Override
	public App_product getProductInfo(Long productId) {
		// 1 先获取商品信息
		App_product product = this.baseMapper.getProductInfo(productId);
		// 2 把商品轮播图加上请求地址
		if(!product.getPictureList().isEmpty()&&product.getPictureList()!=null) {
			for(App_product_picture picture :product.getPictureList()) {
				if(!StringUtils.isEmpty(picture.getImg_url())) {
					picture.setImg_url(serverUrl+picture.getImg_url());
				}
			}
		}
		return product;
	}

	

	

}
