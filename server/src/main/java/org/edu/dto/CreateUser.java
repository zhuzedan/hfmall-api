package org.edu.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author :zzd
 * @date : 2023-01-20 23:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUser {
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

}
