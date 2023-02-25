package org.zzd.controller;

import org.zzd.domain.Activity;
import org.zzd.service.ActivityService;
import org.zzd.utils.PageHelper;
import org.zzd.exception.ResponseException;
import org.zzd.result.ResultCodeEnum;
import org.zzd.result.ResponseResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * (Activity)表控制层
 *
 * @author zzd
 * @since 2023-02-25 13:46:11
 */
@Api(tags = "汉服活动")
@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @ApiOperation(value = "分页查询")
    @PostMapping("/queryPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", dataType = "integer",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", paramType = "query", dataType = "integer",defaultValue = "10"),
            @ApiImplicitParam(name = "startCreateTime", value = "起始日期", paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "endCreateTime", value = "结束日期", paramType = "query", dataType = "date")
    })
    public ResponseResult<PageHelper<Activity>> queryPage(@ApiIgnore @RequestParam HashMap params) {
        return activityService.queryPage(params);
    }

    @ApiOperation(value = "获取详情")
    @GetMapping("/read")
    public ResponseResult selectOne(Integer id) {
        Activity activity = activityService.getById(id);
        if (!Objects.isNull(activity)) {
            return ResponseResult.success(activity);
        }
        else {
            throw new ResponseException(ResultCodeEnum.PARAM_NOT_VALID.getCode(), ResultCodeEnum.PARAM_NOT_VALID.getMessage());
        }
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/save")
    public ResponseResult insert(@RequestBody Activity activity) {
        boolean flag = activityService.save(activity);
        if (flag) {
            return ResponseResult.success();
        }else {
            throw new ResponseException(ResultCodeEnum.DATA_ERROR.getCode(), ResultCodeEnum.DATA_ERROR.getMessage());
        }
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody Activity activity) {
        activityService.updateById(activity);
        return ResponseResult.success();
    }

    @ApiOperation(value = "删除数据")
    @DeleteMapping("delete")
    public ResponseResult delete(Long id) {
        boolean flag = activityService.removeById(id);
        if (flag) {
            return ResponseResult.success();
        }
        else {
            throw new ResponseException(ResultCodeEnum.PARAM_NOT_VALID.getCode(), ResultCodeEnum.PARAM_NOT_VALID.getMessage());
        }
    }
    @ApiOperation(value = "批量删除数据")
    @DeleteMapping("/batchRemove")
    public ResponseResult batchRemove(@RequestBody List<Long> idList) {
        activityService.removeByIds(idList);
        return ResponseResult.success();
    }
}

