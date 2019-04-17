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
 * app店家表
 * </p>
 *
 * @author warren
 * @since 2019-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class App_store implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 店铺图片
     */
    @TableField("img_url")
    private String img_url;

    @TableField("name")
    private String name;

    @TableField("tel")
    private Long tel;

    @TableField("address")
    private String address;

    @TableField("open_time")
    private String open_time;

    @TableField("close_time")
    private String close_time;

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
