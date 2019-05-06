package cn.stylefeng.guns.modular.wedding.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.modular.wedding.entity.App_area;
import cn.stylefeng.guns.modular.wedding.mapper.App_areaMapper;
import cn.stylefeng.guns.modular.wedding.service.IApp_areaService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * app 地区表 服务实现类
 * </p>
 *
 * @author warren
 * @since 2019-04-12
 */
@Service
public class App_areaServiceImpl extends ServiceImpl<App_areaMapper, App_area> implements IApp_areaService {
	
	@Resource
	private App_areaMapper app_areaMapper;
	
	/**
     * 获取菜单树形列表
     *
     * @author fengshuonan
     * @Date 2019/2/23 22:02
     */
	@Override
	public List<Map<String, Object>> selectMenuTree(String condition, String level) {
		List<Map<String, Object>> maps = this.baseMapper.selectMenuTree(condition, level);
		
		if(maps == null) {
			maps = new ArrayList<>();
		}
		
		// 创建根节点
		App_area area = new App_area();
		area.setId(0);
		area.setName("根节点");
		//area.setParent_id(0);
		
		maps.add(BeanUtil.beanToMap(area));
		
		return maps;
	}
	
	/**
     * 删除菜单包含所有子菜单
     *
     * @author stylefeng
     * @Date 2017/6/13 22:02
     */
    @Transactional
    @Override
    public void delMenuContainSubMenus(Long id, String pictureDisk) {

        App_area area = app_areaMapper.selectById(id);
        //删除图片
        File file = new File(pictureDisk + area.getImg_url());
        if(file.exists()) {
        	file.delete();
        }
        //删除当前菜单
        app_areaMapper.deleteById(id);

        //删除所有子菜单
        QueryWrapper<App_area> wrapper = new QueryWrapper<>();
        wrapper = wrapper.like("parent_id", id);
        //app_areaMapper.delete(wrapper);
        List<App_area> app_areas = app_areaMapper.selectList(wrapper);
        if(app_areas!=null && !app_areas.isEmpty()) {
        	for(App_area app_area : app_areas) {
		        //删除图片
		        File f = new File(pictureDisk + area.getImg_url());
		        if(f.exists()) {
		        	f.delete();
		        }
		        app_areaMapper.deleteById(app_area.getId());
        	}
        }
    }

    /**
     * 获取菜单列表树
     *
     * @return
     * @date 2017年2月19日 下午1:33:51
     */
    @Override
    public List<ZTreeNode> menuTreeList() {
        return this.baseMapper.menuTreeList();
    }
	
}
