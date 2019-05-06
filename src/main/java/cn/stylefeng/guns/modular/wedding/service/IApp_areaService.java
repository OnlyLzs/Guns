package cn.stylefeng.guns.modular.wedding.service;

import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.modular.wedding.entity.App_area;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * app 地区表 服务类
 * </p>
 *
 * @author warren
 * @since 2019-04-12
 */
public interface IApp_areaService extends IService<App_area> {
	
	public List<Map<String, Object>> selectMenuTree(String condition, String level);
	
	/**
     * 删除菜单包含所有子菜单
     *
     * @author stylefeng
     * @Date 2017/6/13 22:02
     */
    public void delMenuContainSubMenus(Long id, String pictureDisk);
    
    /**
     * 获取菜单列表树
     *
     * @return
     * @date 2017年2月19日 下午1:33:51
     */
    public List<ZTreeNode> menuTreeList();
}