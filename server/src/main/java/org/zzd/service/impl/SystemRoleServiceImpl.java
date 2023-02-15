package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.zzd.constant.PageConstant;
import org.zzd.domain.SystemUserRole;
import org.zzd.mapper.SystemUserRoleMapper;
import org.zzd.result.ResponseResult;
import org.zzd.utils.PageCountUtil;
import org.zzd.vo.AssignRoleVo;
import org.zzd.utils.PageHelper;
import org.zzd.domain.SystemRole;
import org.zzd.service.SystemRoleService;
import org.zzd.mapper.SystemRoleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 角色(SystemRole)表服务实现类
 *
 * @author zzd
 * @since 2023-01-15 23:43:32
 */
@Service("systemRoleService")
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements SystemRoleService {
    @Autowired
    private SystemUserRoleMapper systemUserRoleMapper;

    @Override
    public ResponseResult<PageHelper<SystemRole>> queryPage(HashMap params) {
        int pageNum = Integer.parseInt((String) params.get(PageConstant.PAGE_NUM));
        int pageSize = Integer.parseInt((String) params.get(PageConstant.PAGE_SIZE));
        Page<SystemRole> page = new Page(pageNum, pageSize);

        LambdaQueryWrapper<SystemRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //角色名
        if (!StringUtils.isBlank((CharSequence) params.get("roleName"))) {
            lambdaQueryWrapper.like(SystemRole::getRoleName,params.get("roleName"));
        }
        // 起始日期
        if(!StringUtils.isBlank((CharSequence) params.get("startCreateTime"))){
            lambdaQueryWrapper.ge(SystemRole::getCreateTime,params.get("startCreateTime"));
        }
        // 结束日期
        if(!StringUtils.isBlank((CharSequence) params.get("endCreateTime"))){
            lambdaQueryWrapper.le(SystemRole::getCreateTime,params.get("endCreateTime"));
        }
        IPage<SystemRole> iPage = this.page(page, lambdaQueryWrapper);
        List<SystemRole> list = iPage.getRecords();
        Long pageCount = PageCountUtil.countPage( iPage.getTotal(), pageSize);
        return ResponseResult.success(new PageHelper<>(iPage.getTotal(),pageCount, iPage.getCurrent(),iPage.getSize(),list));
    }
    //获取用户的角色
    @Override
    public ResponseResult getRolesByUserId(Integer userId) {
        //获取所有角色
        List<SystemRole> roles = baseMapper.selectList(null);
        LambdaQueryWrapper<SystemUserRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SystemUserRole::getUserId,userId);
        //获取当前用户角色
        List<SystemUserRole> userRolesList = systemUserRoleMapper.selectList(lambdaQueryWrapper);
        List<Long> ids= userRolesList.stream().map(item -> item.getRoleId()).collect(Collectors.toList());
        Map<String,Object> map = new HashMap();
        map.put("allRoles", roles);
        map.put("userRoleIds", ids);
        return ResponseResult.success(map);
    }
    //用户分配角色
    @Override
    public ResponseResult doAssign(AssignRoleVo assignRoleVo) {
        //根据id删除之前分配的角色
        LambdaQueryWrapper<SystemUserRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SystemUserRole::getUserId,assignRoleVo.getUserId());
        systemUserRoleMapper.delete(lambdaQueryWrapper);
        //获取所有角色id，添加角色用户关系表
        //角色id列表
        List<String> roleIdList = assignRoleVo.getRoleIdList();
        for (String roleId:roleIdList) {
            SystemUserRole userRole = new SystemUserRole();
            userRole.setUserId(Long.valueOf(assignRoleVo.getUserId()));
            userRole.setRoleId(Long.valueOf(roleId));
            systemUserRoleMapper.insert(userRole);
        }
        return ResponseResult.success();
    }
}

