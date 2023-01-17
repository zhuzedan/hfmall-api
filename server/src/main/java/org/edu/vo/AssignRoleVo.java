package org.edu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

import lombok.Data;

/**
 * @author :zzd
 * @date : 2023-01-17 20:38
 */
@ApiModel(description = "分配菜单")
@Data
public class AssignRoleVo {

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "角色id列表")
    private List<String> roleIdList;

}
