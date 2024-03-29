package org.zzd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :zzd
 * @date : 2023-02-16 8:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {
    //手机号
    private String phone;
    //用户名
    private String username;
    //邮箱
    private String email;
    //密码
    private String password;
}
