package org.zzd.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 产品轮播图图片(ProductSwiperImage)表实体类
 *
 * @author zzd
 * @since 2023-01-04 19:51:06
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_product_swiper_image")
public class ProductSwiperImage  {
    @TableId
    private Integer id;

    //图片
    private String image;
    //排序
    private Integer sort;
    //所属商品id
    private Integer productid;

}

