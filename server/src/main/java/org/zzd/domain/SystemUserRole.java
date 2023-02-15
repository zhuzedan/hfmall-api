package org.zzd.domain;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 用户角色(SystemUserRole)表实体类
 *
 * @author zzd
 * @since 2023-01-17 20:12:57
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_system_user_role")
public class SystemUserRole implements Serializable {
    //主键id
    @TableId
    private Long id;

    //角色id
    private Long roleId;
    //用户id
    private Long userId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //删除标记（0:可用 1:已删除）
    @TableLogic(value = "0",delval = "1")
    private Integer isDeleted;

}

