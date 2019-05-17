package cn.stylefeng.guns.modular.wedding.service;

import cn.stylefeng.guns.modular.wedding.entity.App_user;

import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * app用户表 服务类
 * </p>
 *
 * @author Jason
 * @since 2019-05-15
 */
public interface IApp_userService extends IService<App_user> {

	/**
	 * @Description 获取手机用户列表
	 * @author Jason
	 * @date 2019年5月16日
	 * @param condition
	 * @return
	 */
	Page<Map<String, Object>> selectUsers(String condition);

	/**
	 * @Description
	 * @author Jason
	 * @date 2019年5月16日
	 * @param userId
	 * @param status
	 */
	void setStatus(Long userId, boolean status);

}
