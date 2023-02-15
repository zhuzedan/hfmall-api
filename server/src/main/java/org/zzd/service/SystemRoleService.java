package org.zzd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zzd.domain.SystemRole;
import org.zzd.result.ResponseResult;
import org.zzd.vo.AssignRoleVo;
import org.zzd.utils.PageHelper;

import java.util.HashMap;

/**
 * 角色(SystemRole)表服务接口
 *
 * @author zzd
 * @since 2023-01-15 23:43:32
 */
public interface SystemRoleService extends IService<SystemRole> {
    // 分页查询
    ResponseResult<PageHelper<SystemRole>> queryPage(HashMap params);
    //获取用户的角色
    ResponseResult getRolesByUserId(Integer userId);
    //用户分配角色
    ResponseResult doAssign(AssignRoleVo assignRoleVo);
}

