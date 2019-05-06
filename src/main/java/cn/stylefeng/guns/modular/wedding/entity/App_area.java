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
 * app 地区表
 * </p>
 *
 * @author warren
 * @since 2019-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class App_area implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("parent_id")
    private Integer parent_id;

    /**
     * 标注 几级菜单
     */
    @TableField("level")
    private String level;
    
    @TableField("img_url")
    private String img_url;
    
    @TableField("status")
    private Integer status;

    @TableField("sort")
    private String sort;

    @TableField("create_time")
    private String create_time;

    @TableField("update_time")
    private String update_time;


}
