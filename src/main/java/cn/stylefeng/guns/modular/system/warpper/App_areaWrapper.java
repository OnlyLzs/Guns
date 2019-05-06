/**
 * @date 11:01:18 AM
 * App_areaWrapper.java
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
 * @class App_areaWrapper	
 * @author Warren
 * @description
 * @date Apr 28, 2019 
 */
public class App_areaWrapper extends BaseControllerWrapper{

	public App_areaWrapper(Map<String, Object> single) {
		super(single);
	}

    public App_areaWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public App_areaWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public App_areaWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

	@Override
	protected void wrapTheMap(Map<String, Object> map) {
		String img_url = (String)map.get("img_url");
		if(img_url!=null && !img_url.equals("")) {
			map.put("img_url", ConstantFactory.me().getPicutureRequestPath((String)map.get("img_url")));
		}
	}

}
