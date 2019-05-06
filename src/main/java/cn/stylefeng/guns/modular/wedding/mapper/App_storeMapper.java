package cn.stylefeng.guns.modular.wedding.mapper;

import cn.stylefeng.guns.modular.wedding.entity.App_store;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * app店家表 Mapper 接口
 * </p>
 *
 * @author warren
 * @since 2019-04-12
 */
public interface App_storeMapper extends BaseMapper<App_store> {
	
	Page<Map<String, Object>> selectStores(@Param("page") Page page, @Param("condition") String condition);
}
