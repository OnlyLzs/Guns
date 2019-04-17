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
 * app 轮播图表
 * </p>
 *
 * @author warren
 * @since 2019-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class App_picture implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("img_url")
    private String img_url;

    /**
     * 图片请求地址
     */
    @TableField("link_url")
    private String link_url;

    @TableField("option_id")
    private Integer option_id;

    /**
     * 状态
     */
    @TableField("status")
    private Boolean status;

    @TableField("remark")
    private String remark;

    @TableField("sort")
    private String sort;

    @TableField("flag")
    private String flag;

    @TableField("create_time")
    private String create_time;

    @TableField("update_time")
    private String update_time;


}
