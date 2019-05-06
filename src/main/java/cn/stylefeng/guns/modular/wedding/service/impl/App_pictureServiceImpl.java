package cn.stylefeng.guns.modular.wedding.service.impl;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.wedding.entity.App_picture;
import cn.stylefeng.guns.modular.wedding.mapper.App_pictureMapper;
import cn.stylefeng.guns.modular.wedding.service.IApp_pictureService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 * app 轮播图表 服务实现类
 * </p>
 *
 * @author warren
 * @since 2019-04-12
 */
@Service
public class App_pictureServiceImpl extends ServiceImpl<App_pictureMapper, App_picture> implements IApp_pictureService {
	
	
	/**
     * 根据条件查询轮播图列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public Page<Map<String, Object>> selectPictures(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.selectPictures(page, condition);
    }
}
