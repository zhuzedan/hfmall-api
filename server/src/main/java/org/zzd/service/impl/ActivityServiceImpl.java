package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.zzd.constant.PageConstant;
import org.zzd.result.ResponseResult;
import org.zzd.utils.PageHelper;
import org.zzd.domain.Activity;
import org.zzd.service.ActivityService;
import org.zzd.mapper.ActivityMapper;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * (Activity)表服务实现类
 *
 * @author zzd
 * @since 2023-02-25 13:46:11
 */
@Service("activityService")
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public ResponseResult<PageHelper<Activity>> queryPage(HashMap params) {
        int pageNum = Integer.parseInt((String) params.get(PageConstant.PAGE_NUM));
        int pageSize = Integer.parseInt((String) params.get(PageConstant.PAGE_SIZE));
        Page<Activity> page = new Page(pageNum, pageSize);

        LambdaQueryWrapper<Activity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 起始日期
        if(!StringUtils.isBlank((CharSequence) params.get("startCreateTime"))){
            lambdaQueryWrapper.ge(Activity::getCreateTime,params.get("startCreateTime"));
        }
        // 结束日期
        if(!StringUtils.isBlank((CharSequence) params.get("endCreateTime"))){
            lambdaQueryWrapper.le(Activity::getCreateTime,params.get("endCreateTime"));
        }

        IPage<Activity> iPage = this.page(page, lambdaQueryWrapper);
        return ResponseResult.success(PageHelper.restPage(iPage));
    }

    @Override
    public ResponseResult queryActivities() {
        LambdaQueryWrapper<Activity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<Activity> activities = activityMapper.selectList(lambdaQueryWrapper);

        return ResponseResult.success(activities);
    }

    @Override
    public ResponseResult joinActivity(String id) {
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Activity activity = activityMapper.selectOne(queryWrapper);
        System.out.println(activity);
        Integer num = activity.getNum();
        num+=1;
        activity.setNum(num);
        activityMapper.updateById(activity);
        return ResponseResult.success(activity);
    }
}

