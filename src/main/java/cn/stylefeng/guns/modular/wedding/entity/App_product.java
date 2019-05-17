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
 * app商品表： 婚礼 旅拍 
 * </p>
 *
 * @author warren
 * @since 2019-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class App_product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 系统生成编号
     */
    @TableField("numb")
    private String numb;

    /**
     * 承办的店家的id
     */
    @TableField("store_id")
    private Integer store_id;

    /**
     * 目标城市 （外键）
     */
    @TableField("target_city")
    private Integer target_city;

    /**
     * 出发城市
     */
    @TableField("start_city")
    private String start_city;

    @TableField("title")
    private String title;

    @TableField("description")
    private String description;

    @TableField("price")
    private BigDecimal price;

    /**
     * 产品详情
     */
    @TableField("product_detail_text")
    private String product_detail_text;

    /**
     * 案例详情图片
     */
    @TableField("example_img")
    private String example_img;

    /**
     * 婚礼配置
     */
    @TableField("config")
    private String config;

    /**
     * 费用明细
     */
    @TableField("price_detial")
    private String price_detial;

    /**
     * 类型 1 婚礼 2 旅拍 3蜜月...
     */
    @TableField("type")
    private Integer type;
    
    /**
     * 店铺信息
     */
    @TableField(exist=false)
    private App_store store;
    
    @TableField(exist=false)
    private App_area area;
    
    @TableField(exist=false)
    List<App_product_picture> pictureList;


}
