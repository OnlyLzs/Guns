/**
 * @date 9:42:48 AM
 * App_storeWrapper.java
 * Administrator
 * TODO
 */
package cn.stylefeng.guns.modular.system.warpper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;

/**
 * @class App_storeWrapper	
 * @author Warren
 * @description
 * @date May 6, 2019 
 */
public class App_storeWrapper extends BaseControllerWrapper{
	
	public App_storeWrapper(Map<String, Object> single) {
		super(single);
  	}

    public App_storeWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public App_storeWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public App_storeWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }
	   
	@Override
	protected void wrapTheMap(Map<String, Object> map) {
		map.put("img_url", ConstantFactory.me().getPicutureRequestPath((String)map.get("img_url")));
	}
}
