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
 * (Category)表实体类
 *
 * @author zzd
 * @since 2023-01-04 19:01:26
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_category")
public class Category  {
    @TableId
    private Integer id;

    //分类名称
    private String name;
    //状态
    private Integer status;
    //描述
    private String remark;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //排序
    private Integer sort;
    //大分类下的所有小类
    @TableField(select = false)
    private List<SubCategory> subCategoryList;

}

