package org.edu.domain;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 用户表(SystemUser)表实体类
 *
 * @author zzd
 * @since 2023-01-05 23:10:13
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_system_user")
public class SystemUser  {
    @TableId
    private Long id;

    //用户名
    private String username;
    //密码
    private String password;
    //姓名
    private String name;
    //手机
    private String phone;
    //头像地址
    private String headUrl;
    //描述
    private String description;
    //状态（1：正常 0：停用）
    private Integer status;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //删除标记（0:可用 1:已删除）
    private Integer isDeleted;

}

