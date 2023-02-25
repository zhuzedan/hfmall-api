package org.zzd.domain;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Activity)表实体类
 *
 * @author zzd
 * @since 2023-02-25 13:46:11
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_activity")
public class Activity implements Serializable {
    //主键id@TableId
    private Integer id;

    //活动名称
    private String name;
    //参与人数
    private Integer num;
    //粉丝
    private String funs;
    //活动图片
    private String img;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //状态
    private String status;
    //是否删除0没删1删了
    private String isDeleted;

}

