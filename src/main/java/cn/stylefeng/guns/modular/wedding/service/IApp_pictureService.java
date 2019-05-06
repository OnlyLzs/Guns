package cn.stylefeng.guns.modular.wedding.service;

import cn.stylefeng.guns.modular.wedding.entity.App_picture;

import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * app 轮播图表 服务类
 * </p>
 *
 * @author warren
 * @since 2019-04-12
 */
public interface IApp_pictureService extends IService<App_picture> {
	
	public Page<Map<String, Object>> selectPictures(String condition);
}
