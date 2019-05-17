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
 * app用户表
 * </p>
 *
 * @author Jason
 * @since 2019-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class App_user implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    @TableField("status")
    private Integer status;

    @TableField("name")
    private String name;

    @TableField("tel")
    private Long tel;

    @TableField("email")
    private String email;

    @TableField("qq")
    private String qq;

    @TableField("qq_img")
    private String qq_img;

    @TableField("login_name")
    private String login_name;

    @TableField("login_password")
    private String login_password;

    @TableField("token")
    private String token;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private String create_time;

    @TableField("update_time")
    private String update_time;


}
