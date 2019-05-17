package cn.stylefeng.guns.modular.wedding.entity;

import java.math.BigDecimal;
import java.util.List;

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
 * @author Jason
 * @since 2019-05-16
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
    
    /**
     * 出发城市
     */
    @TableField("start_city")
    private String start_city;
    
    
    /**
     * 出发日期
     */
    @TableField("start_date")
    private String start_date;
    
    /**
     * 购买人信息
     */
    @TableField("buyer_name")
    private String buyer_name;

    @TableField("buyer_mobile")
    private String buyer_mobile;

    @TableField("buyer_email")
    private String buyer_email;

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

    @TableField(exist=false)
    App_store store;
    
    @TableField(exist=false)
    App_product product;
    
    @TableField(exist=false)
    List<App_passenger> passengers;
   


}
