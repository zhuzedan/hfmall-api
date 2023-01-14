package org.edu.domain;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.edu.dto.RegisterUser;

/**
 * 前台用户登录(User)表实体类
 *
 * @author zzd
 * @since 2023-01-13 22:52:06
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")
public class User implements Serializable {
    @TableId
    private Integer id;

    //用户名
    private String username;
    //密码
    private String password;
    //昵称
    private String nickname;
    //电子邮箱
    private String email;
    //头像
    private String avatar;
    //等级
    private Integer level;
    //性别;1=男,2=女,3=未知
    private Integer gender;
    //生日
    private Date birthday;
    //登录ip
    private String loginIp;
    //登录时间
    private Integer loginTime;
    //创建时间
    private Integer createTime;
    //更新时间
    private Integer updateTime;
    //删除时间
    private String deleteTime;
    //状态
    private Integer status;
    //身高（厘米）
    private String height;
    //体重（斤）
    private String weight;
    //手机号码
    private String phone;

    public User(RegisterUser registerUser) {
        this.username = registerUser.getUsername();
        this.password = registerUser.getPassword();
        this.phone = registerUser.getPhone();
        this.email = registerUser.getEmail();
    }

}

