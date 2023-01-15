package org.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.edu.constant.PageConstant;
import org.edu.result.ResponseResult;
import org.edu.utils.PageCountUtil;
import org.edu.vo.RespPageBean;
import org.edu.domain.SystemRole;
import org.edu.service.SystemRoleService;
import org.edu.mapper.SystemRoleMapper;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 角色(SystemRole)表服务实现类
 *
 * @author zzd
 * @since 2023-01-15 23:43:32
 */
@Service("systemRoleService")
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements SystemRoleService {

    @Override
    public ResponseResult<RespPageBean<SystemRole>> queryPage(HashMap params) {
        int pageNum = Integer.parseInt((String) params.get(PageConstant.PAGE_NUM));
        int pageSize = Integer.parseInt((String) params.get(PageConstant.PAGE_SIZE));
        Page<SystemRole> page = new Page(pageNum, pageSize);

        LambdaQueryWrapper<SystemRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        IPage<SystemRole> iPage = this.page(page, lambdaQueryWrapper);
        List<SystemRole> list = iPage.getRecords();
        Long pageCount = PageCountUtil.countPage( iPage.getTotal(), pageSize);
        return ResponseResult.success(new RespPageBean<>(iPage.getTotal(),pageCount, iPage.getCurrent(),iPage.getSize(),list));
    }
}

