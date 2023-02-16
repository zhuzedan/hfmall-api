package org.zzd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :zzd
 * @date : 2023-02-16 16:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDto {
    //商品名称
    private String name;
    //商品价格
    private Double price;
    //库存
    private Integer stock;
    //商品图片
    private String propic;
    //商品描述
    private String description;
}
