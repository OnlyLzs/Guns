package cn.stylefeng.guns.modular.wedding.service.impl;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.wedding.entity.App_store;
import cn.stylefeng.guns.modular.wedding.mapper.App_storeMapper;
import cn.stylefeng.guns.modular.wedding.service.IApp_storeService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 * app店家表 服务实现类
 * </p>
 *
 * @author warren
 * @since 2019-04-12
 */
@Service
public class App_storeServiceImpl extends ServiceImpl<App_storeMapper, App_store> implements IApp_storeService {

	@Override
	public Page<Map<String, Object>> selectStores(String condition) {
		Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.selectStores(page, condition);
	}
}
