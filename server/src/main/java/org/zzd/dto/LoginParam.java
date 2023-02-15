package org.zzd.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户登录实体类
 * @author :zzd
 * @date : 2023/1/5
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "登录对象")
public class LoginParam implements Serializable {
    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
