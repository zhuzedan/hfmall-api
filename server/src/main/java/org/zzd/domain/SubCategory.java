package org.zzd.domain;

import java.util.Date;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 二级分类(SubCategory)表实体类
 *
 * @author zzd
 * @since 2023-01-07 22:34:01
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_sub_category")
public class SubCategory  {
    @TableId
    private Integer id;

    //名称
    private String name;
    //备注
    private String remark;
    //状态
    private Integer status;
    //一级分类id
    private Integer categoryid;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //是否删除
    private Integer isDeleted;
    //分类下的商品集合
    @TableField(select = false)
    private List<Product> productList;

}

