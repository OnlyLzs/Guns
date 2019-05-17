package cn.stylefeng.guns.modular.wedding.mapper;

import cn.stylefeng.guns.modular.wedding.entity.App_order;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * app 订单表 Mapper 接口
 * </p>
 *
 * @author Jason
 * @since 2019-05-16
 */
public interface App_orderMapper extends BaseMapper<App_order> {

	/**
	 * @Description 查询订单
	 * @author Jason
	 * @date 2019年5月16日
	 * @param page
	 * @param condition
	 * @return
	 */
	Page<Map<String, Object>> selectOrders(@Param("page")Page page, @Param("condition")String condition, @Param("timeLimit")String timeLimit, @Param("orderStatus")String orderStatus);

	/**
	 * @Description 查询退款订单
	 * @author Jason
	 * @date 2019年5月16日
	 * @param page
	 * @param condition
	 * @param timeLimit
	 * @param orderStatus
	 * @return
	 */
	Page<Map<String, Object>> selectDrawBackOrders(@Param("page")Page page, @Param("condition")String condition, @Param("timeLimit")String timeLimit, @Param("orderStatus")String orderStatus);

	/**
	 * @Description 设置订单状态
	 * @author Jason
	 * @date 2019年5月16日
	 * @param orderId
	 * @param status
	 */
	void setStatus(@Param("orderId")Long orderId, @Param("status")String status);

	/**
	 * @Description 显示订单详情
	 * @author Jason
	 * @date 2019年5月16日
	 * @param orderId
	 * @return
	 */
	App_order showDetail(Long orderId);

	/**
	 * @Description 获取乘客详情
	 * @author Jason
	 * @param page 
	 * @date 2019年5月17日
	 * @param orderId
	 * @return
	 */
	Page<Map<String, Object>> selectPassengers(@Param("page")Page page, @Param("orderId")Long orderId);

}
