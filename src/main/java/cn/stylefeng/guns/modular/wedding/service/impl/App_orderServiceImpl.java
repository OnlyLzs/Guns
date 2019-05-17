package cn.stylefeng.guns.modular.wedding.service.impl;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.wedding.entity.App_order;
import cn.stylefeng.guns.modular.wedding.mapper.App_orderMapper;
import cn.stylefeng.guns.modular.wedding.service.IApp_orderService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 * app 订单表 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2019-05-16
 */
@Service
public class App_orderServiceImpl extends ServiceImpl<App_orderMapper, App_order> implements IApp_orderService {

	@Override
	public Page<Map<String, Object>> selectOrders(String condition, String timeLimit, String orderStatus) {
		Page page = LayuiPageFactory.defaultPage();
		return this.baseMapper.selectOrders(page, condition, timeLimit, orderStatus);
	}

	@Override
	public Page<Map<String, Object>> selectDrawBackOrders(String condition, String timeLimit, String orderStatus) {
		Page page = LayuiPageFactory.defaultPage();
		return this.baseMapper.selectDrawBackOrders(page, condition, timeLimit, orderStatus);
	}

	@Override
	public void setStatus(Long orderId, String status) {
		this.baseMapper.setStatus(orderId, status);
	}

	@Override
	public App_order showDetail(Long orderId) {
	
		return this.baseMapper.showDetail(orderId);
	}

	@Override
	public Page<Map<String, Object>> selectPassengers(Long orderId) {
		Page page = LayuiPageFactory.defaultPage();
		return this.baseMapper.selectPassengers(page,orderId);
	}

}
