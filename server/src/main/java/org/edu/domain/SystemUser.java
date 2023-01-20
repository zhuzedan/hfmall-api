package org.edu.domain;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.edu.dto.CreateUser;
import org.edu.dto.RegisterUser;

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
public class SystemUser implements Serializable {
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

    public SystemUser(RegisterUser registerUser) {
        this.username = registerUser.getUsername();
        this.password = registerUser.getPassword();
        this.phone = registerUser.getPhone();
        this.email = registerUser.getEmail();
    }

    public SystemUser(CreateUser createUser) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.headUrl = headUrl;
        this.description = description;
        this.status = status;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
        this.loginIp = loginIp;
        this.isAppuser = isAppuser;
    }
}

