package cn.stylefeng.guns.modular.wedding.service.impl;

import cn.stylefeng.guns.core.common.constant.state.ProductStatus;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.wedding.entity.App_user;
import cn.stylefeng.guns.modular.wedding.mapper.App_userMapper;
import cn.stylefeng.guns.modular.wedding.service.IApp_userService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * app用户表 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2019-05-15
 */
@Service
public class App_userServiceImpl extends ServiceImpl<App_userMapper, App_user> implements IApp_userService {

	
	@Override
	public Page<Map<String, Object>> selectUsers(String condition) {
		Page page = LayuiPageFactory.defaultPage();
		return this.baseMapper.selectUsers(page, condition);
	}

	@Override
	public void setStatus(Long userId, boolean status) {
		if(status) {
			int result = this.baseMapper.setStatus(userId, ProductStatus.OK.getCode());
		}else {
			int result = this.baseMapper.setStatus(userId, ProductStatus.FREEZED.getCode());
		}
	}

}
