package org.zzd.domain;

import java.util.Collection;
import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.*;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.zzd.dto.RegisterUserDto;

/**
 * 用户表(SystemUser)表实体类
 *
 * @author zzd
 * @since 2023-01-15 22:39:45
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_system_user")
public class SystemUser implements Serializable, UserDetails {
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
    @Getter(value = AccessLevel.NONE)
    private String enabled;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //删除标记（0:可用 1:已删除）
    @TableLogic(value = "0",delval = "1")
    private Integer isDeleted;
    //昵称
    private String nickname;
    //邮箱
    private String email;
    //性别;1=男,2=女,3=未知
    private Integer gender;
    //生日
    private Date birthday;
    //登录ip
    private String loginIp;
    //是否前台app用户
    private Integer isAppuser;

    // private List<String> permissions;


    public SystemUser(RegisterUserDto registerUser) {
        this.username = registerUser.getUsername();
        this.password = registerUser.getPassword();
        this.phone = registerUser.getPhone();
        this.email = registerUser.getEmail();
    }

    public SystemUser(SystemUser systemUser) {
        this.id = systemUser.getId();
        this.username = systemUser.getUsername();
        this.password = systemUser.getPassword();
        this.name = systemUser.getName();
        this.phone = systemUser.getPhone();
        this.email = systemUser.getEmail();
        this.headUrl = systemUser.getHeadUrl();
        this.description = systemUser.getDescription();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

