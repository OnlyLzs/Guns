package cn.stylefeng.guns.modular.wedding.mapper;

import cn.stylefeng.guns.modular.wedding.entity.App_picture;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * app 轮播图表 Mapper 接口
 * </p>
 *
 * @author warren
 * @since 2019-04-12
 */
public interface App_pictureMapper extends BaseMapper<App_picture> {
	 /**
     * 根据条件查询轮播图列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    Page<Map<String, Object>> selectPictures(@Param("page") Page page, @Param("condition") String condition);
}
