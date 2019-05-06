/**
 * @date 7:12:16 PM
 * App_pictureWrapper.java
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
 * @class App_pictureWrapper	
 * @author Warren
 * @description
 * @date Apr 19, 2019 
 */
public class App_pictureWrapper extends BaseControllerWrapper{

	public App_pictureWrapper(Map<String, Object> single) {
		super(single);
  	}

    public App_pictureWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public App_pictureWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public App_pictureWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }
	   
	@Override
	protected void wrapTheMap(Map<String, Object> map) {
		map.put("img_req", ConstantFactory.me().getPicutureRequestPath((String)map.get("img_url")));
	}

}
