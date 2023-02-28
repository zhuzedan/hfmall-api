package org.zzd.domain;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (News)表实体类
 *
 * @author zzd
 * @since 2023-02-22 21:16:25
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_news")
public class News implements Serializable {
    //主键
    @TableId
    private Long id;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //发表时间
    private Date publishTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //资讯标题
    private String title;
    //资讯内容
    private String content;
    //浏览量
    private Integer click;
    //文章图片
    private String image;
    //是否删除0没删1删了
    @TableLogic(value = "0",delval = "1")
    private Integer isDeleted;

}

