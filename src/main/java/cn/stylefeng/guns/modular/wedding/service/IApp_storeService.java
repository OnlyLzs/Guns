package cn.stylefeng.guns.modular.wedding.service;

import cn.stylefeng.guns.modular.wedding.entity.App_store;

import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * app店家表 服务类
 * </p>
 *
 * @author warren
 * @since 2019-04-12
 */
public interface IApp_storeService extends IService<App_store> {
	public Page<Map<String, Object>> selectStores(String condition);
}
