package org.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author :zzd
 * @date : 2023/1/5
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginParam implements Serializable {
    //用户名
    private String username;
    //密码
    private String password;
}
