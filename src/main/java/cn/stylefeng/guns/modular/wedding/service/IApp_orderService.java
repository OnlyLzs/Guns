package cn.stylefeng.guns.modular.wedding.service;

import cn.stylefeng.guns.modular.wedding.entity.App_order;

import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * app 订单表 服务类
 * </p>
 *
 * @author Jason
 * @since 2019-05-16
 */
public interface IApp_orderService extends IService<App_order> {

	/**
	 * @Description 查询订单列表
	 * @author Jason
	 * @date 2019年5月16日
	 * @param condition
	 * @param orderStatus 
	 * @param timeLimit 
	 * @return
	 */
	Page<Map<String, Object>> selectOrders(String condition, String timeLimit, String orderStatus);

	/**
	 * @Description 查询退款订单
	 * @author Jason
	 * @date 2019年5月16日
	 * @param condition
	 * @param orderStatus 
	 * @param timeLimit 
	 * @return
	 */
	Page<Map<String, Object>> selectDrawBackOrders(String condition, String timeLimit, String orderStatus);

	/** 
	 * @Description 改变订单状态
	 * @author Jason
	 * @date 2019年5月16日
	 * @param orderId
	 * @param status
	 */
	void setStatus(Long orderId, String status);

	/**
	 * @Description 显示订单详情
	 * @author Jason
	 * @date 2019年5月16日
	 * @param orderId
	 * @return 
	 */
	App_order showDetail(Long orderId);

	Page<Map<String, Object>> selectPassengers(Long orderId);

}
