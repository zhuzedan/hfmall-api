package org.edu.domain;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 轮播图(Swiper)表实体类
 *
 * @author zzd
 * @since 2023-01-03 22:40:32
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_swiper")
public class Swiper  {
    @TableId
    private Integer id;

    //名称
    private String name;
    //图片地址
    private String pic;
    //描述
    private String description;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //删除标记（0:可用 1:已删除）
    private Integer isDeleted;
    //排序
    private Integer sort;
    //状态
    private Integer status;
    //url地址
    private String url;

}

