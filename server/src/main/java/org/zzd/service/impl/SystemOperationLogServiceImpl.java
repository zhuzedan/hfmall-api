package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.zzd.constant.PageConstant;
import org.zzd.result.ResponseResult;
import org.zzd.utils.PageHelper;
import org.zzd.domain.SystemOperationLog;
import org.zzd.service.SystemOperationLogService;
import org.zzd.mapper.SystemOperationLogMapper;

import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * (SystemOperationLog)表服务实现类
 *
 * @author zzd
 * @since 2023-02-15 11:57:22
 */
@Service("systemOperationLogService")
public class SystemOperationLogServiceImpl extends ServiceImpl<SystemOperationLogMapper, SystemOperationLog> implements SystemOperationLogService {

    @Override
    public ResponseResult<PageHelper<SystemOperationLog>> queryPage(HashMap params) {
        int pageNum = Integer.parseInt((String) params.get(PageConstant.PAGE_NUM));
        int pageSize = Integer.parseInt((String) params.get(PageConstant.PAGE_SIZE));
        Page<SystemOperationLog> page = new Page(pageNum, pageSize);

        LambdaQueryWrapper<SystemOperationLog> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 起始日期
        if(!StringUtils.isBlank((CharSequence) params.get("startCreateTime"))){
            lambdaQueryWrapper.ge(SystemOperationLog::getCreateTime,params.get("startCreateTime"));
        }
        // 结束日期
        if(!StringUtils.isBlank((CharSequence) params.get("endCreateTime"))){
            lambdaQueryWrapper.le(SystemOperationLog::getCreateTime,params.get("endCreateTime"));
        }

        IPage<SystemOperationLog> iPage = this.page(page, lambdaQueryWrapper);
        return ResponseResult.success(PageHelper.restPage(iPage));
    }
}

