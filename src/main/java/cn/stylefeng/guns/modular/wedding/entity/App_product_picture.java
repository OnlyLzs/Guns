package cn.stylefeng.guns.modular.wedding.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jason
 * @since 2019-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class App_product_picture implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("img_url")
    private String img_url;

    @TableField("mark")
    private String mark;

    @TableField("sort")
    private String sort;

    @TableField("product_id")
    private Integer product_id;
    
    

	public App_product_picture(String img_url, Integer product_id) {
		super();
		this.img_url = img_url;
		this.product_id = product_id;
	}



	public App_product_picture() {
		super();
	}
    
    


}
