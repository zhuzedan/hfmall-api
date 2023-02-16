package org.zzd.domain;

import java.util.Date;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.zzd.dto.CreateProductDto;

/**
 * 商品表(Product)表实体类
 *
 * @author zzd
 * @since 2023-01-07 22:55:50
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_product")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Product implements Serializable {
    @TableId
    private Integer id;

    //商品名称
    private String name;
    //商品价格
    private Double price;
    //库存
    private Integer stock;
    //商品图片
    private String propic;
    //是否热门推荐商品
    private Integer ishot;
    //设置热卖日期
    private Date hotdatetime;
    //商品介绍图片
    private String productintroimgs;
    //商品规格参数图片
    private String productparaimgs;
    //商品描述
    private String description;
    //商品类别编号
    private Integer subCategoryId;
    //删除标记（0:可用 1:已删除）
    @TableLogic(value = "0",delval = "1")
    private Integer isDeleted;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //商品图片轮播
    @TableField(select = false)
    private List<ProductSwiperImage> productSwiperImageList;
    //商品类别
    @TableField(select = false)
    private SubCategory subCategory;

    public Product(CreateProductDto createProductDto) {
        this.name = createProductDto.getName();
        this.price = createProductDto.getPrice();
        this.stock = createProductDto.getStock();
        this.propic = createProductDto.getPropic();
        this.description = createProductDto.getDescription();
    }

}

