package org.edu.domain;

import java.util.Date;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 菜单表(SystemMenu)表实体类
 *
 * @author zzd
 * @since 2023-01-06 22:01:51
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_system_menu")
public class SystemMenu  {
    @TableId
    private Long id;

    //所属上级
    private Long parentId;
    //名称
    private String name;
    //类型(0:目录,1:菜单,2:按钮)
    private Integer type;
    //路由地址
    private String path;
    //组件路径
    private String component;
    //权限标识
    private String perms;
    //图标
    private String icon;
    //排序
    private Integer sortValue;
    //状态(0:禁止,1:正常)
    private Integer status;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //删除标记（0:可用 1:已删除）
    private Integer isDeleted;
    // 下级列表
    @TableField(exist = false)
    private List<SystemMenu> children;
    //是否选中
    @TableField(exist = false)
    private boolean isSelect;

}

