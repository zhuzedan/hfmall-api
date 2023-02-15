package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.zzd.domain.SystemMenu;
import org.zzd.exception.ResponseException;
import org.zzd.mapper.SystemMenuMapper;
import org.zzd.result.ResponseResult;
import org.zzd.result.ResultCodeEnum;
import org.zzd.service.SystemMenuService;
import org.zzd.utils.MenuHelper;
import org.zzd.vo.RouterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单表(SystemMenu)表服务实现类
 *
 * @author zzd
 * @since 2023-01-06 22:01:51
 */
@Service("systemMenuService")
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuMapper, SystemMenu> implements SystemMenuService {
    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Override
    public List<RouterVo> getUserMenuList(Long id) {
        return null;
    }

    @Override
    public List<String> getUserButtonList(Long id) {
        return null;
    }

    @Override
    public ResponseResult findNodes() {
        //全部权限列表
        List<SystemMenu> sysMenuList = this.list();
        if (CollectionUtils.isEmpty(sysMenuList)) {
            return null;
        }
        //构建树形数据
        List<SystemMenu> result = MenuHelper.buildTree(sysMenuList);
        return ResponseResult.success(result);
    }

    @Override
    public ResponseResult removeMenuById(Long id) {
        QueryWrapper<SystemMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Long count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new ResponseException(ResultCodeEnum.NODE_ERROR.getCode(), ResultCodeEnum.NODE_ERROR.getMessage());
        }
        systemMenuMapper.deleteById(id);
        return ResponseResult.success();
    }
}

