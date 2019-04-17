package cn.stylefeng.guns.modular.wedding.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * app 订单表
 * </p>
 *
 * @author warren
 * @since 2019-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class App_order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    @TableField("order_num")
    private String order_num;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer user_id;

    /**
     * 商品id  婚礼 旅拍 蜜月。。。
     */
    @TableField("product_id")
    private Integer product_id;

    @TableField("img_url")
    private String img_url;

    /**
     * 跳转地址
     */
    @TableField("link_url")
    private String link_url;

    /**
     * 实付金额
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 订单状态 未支付 已支付 待出行  已取消
     */
    @TableField("status")
    private Integer status;

    @TableField("sort")
    private String sort;

    @TableField("create_time")
    private String create_time;

    @TableField("update_time")
    private String update_time;


}
