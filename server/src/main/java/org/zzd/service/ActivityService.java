package org.zzd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zzd.domain.Activity;
import org.zzd.result.ResponseResult;
import org.zzd.utils.PageHelper;

import java.util.HashMap;

/**
 * (Activity)表服务接口
 *
 * @author zzd
 * @since 2023-02-25 13:46:11
 */
public interface ActivityService extends IService<Activity> {
    // 分页查询
    ResponseResult<PageHelper<Activity>> queryPage(HashMap params);

    ResponseResult queryActivities();

    ResponseResult joinActivity(String id);
}

