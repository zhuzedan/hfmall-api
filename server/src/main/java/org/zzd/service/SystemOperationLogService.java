package org.zzd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zzd.domain.SystemOperationLog;
import org.zzd.result.ResponseResult;
import org.zzd.utils.PageHelper;

import java.util.HashMap;

/**
 * (SystemOperationLog)表服务接口
 *
 * @author zzd
 * @since 2023-02-15 11:57:22
 */
public interface SystemOperationLogService extends IService<SystemOperationLog> {
    // 分页查询
    ResponseResult<PageHelper<SystemOperationLog>> queryPage(HashMap params);
}

