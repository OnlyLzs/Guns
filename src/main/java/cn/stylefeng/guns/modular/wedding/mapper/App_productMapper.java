package cn.stylefeng.guns.modular.wedding.mapper;

import cn.stylefeng.guns.modular.wedding.entity.App_product;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * app商品表： 婚礼 旅拍  Mapper 接口
 * </p>
 *
 * @author warren
 * @since 2019-04-12
 */
public interface App_productMapper extends BaseMapper<App_product> {

	Page<Map<String, Object>> selectProducts(@Param("page")Page page, @Param("condition")String condition);

	int setStatus(@Param("productId")Long productId, @Param("status")String status);

	App_product getProductInfo(@Param("productId")Long productId);

}
