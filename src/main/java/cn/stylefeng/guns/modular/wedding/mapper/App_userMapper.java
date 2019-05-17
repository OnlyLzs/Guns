package cn.stylefeng.guns.modular.wedding.mapper;

import cn.stylefeng.guns.modular.wedding.entity.App_user;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * app用户表 Mapper 接口
 * </p>
 *
 * @author Jason
 * @since 2019-05-15
 */
public interface App_userMapper extends BaseMapper<App_user> {

	/**
	 * @Description 查询手机端用户dao
	 * @author Jason
	 * @date 2019年5月16日
	 * @param page
	 * @param condition
	 * @return
	 */
	Page<Map<String, Object>> selectUsers(@Param("page")Page page, @Param("condition")String condition);

	/**
	 * @Description 设置用户状态
	 * @author Jason
	 * @date 2019年5月16日
	 * @param userId
	 * @param code
	 * @return
	 */
	int setStatus(@Param("userId")Long userId, @Param("status")String status);

}
