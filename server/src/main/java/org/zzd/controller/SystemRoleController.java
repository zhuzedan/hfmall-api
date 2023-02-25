package org.zzd.controller;

import org.zzd.annotation.Log;
import org.zzd.domain.SystemRole;
import org.zzd.enums.BusinessType;
import org.zzd.enums.OperatorType;
import org.zzd.exception.ResponseException;
import org.zzd.result.ResultCodeEnum;
import org.zzd.service.SystemRoleService;
import org.zzd.utils.PageHelper;
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
 * 角色(SystemRole)表控制层
 *
 * @author zzd
 * @since 2023-01-15 23:46:44
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/api/systemRole")
public class SystemRoleController {

    @Autowired
    private SystemRoleService systemRoleService;

    @Log(title = "查询角色", businessType = BusinessType.SELECT, operatorType = OperatorType.MANAGE)
    @ApiOperation(value = "分页查询")
    @PostMapping("/queryPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", dataType = "integer",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", paramType = "query", dataType = "integer",defaultValue = "10"),
            @ApiImplicitParam(name = "roleName", value = "角色名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "startCreateTime", value = "起始日期", paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "endCreateTime", value = "结束日期", paramType = "query", dataType = "date"),
    })
    public ResponseResult<PageHelper<SystemRole>> queryPage(@ApiIgnore @RequestParam HashMap params) {
        return systemRoleService.queryPage(params);
    }

    @Log(title = "获取用户角色详情", businessType = BusinessType.READ, operatorType = OperatorType.MANAGE)
    @ApiOperation(value = "获取详情")
    @GetMapping("/read")
    public ResponseResult selectOne(Integer id) {
        SystemRole systemRole = systemRoleService.getById(id);
        if (!Objects.isNull(systemRole)) {
            return ResponseResult.success(systemRole);
        }else {
            throw new ResponseException(ResultCodeEnum.PARAM_NOT_VALID.getCode(), ResultCodeEnum.PARAM_NOT_VALID.getMessage());
        }
    }

    @Log(title = "新增角色",businessType = BusinessType.INSERT,operatorType = OperatorType.MANAGE)
    @ApiOperation(value = "新增数据")
    @PostMapping("/save")
    public ResponseResult insert(@RequestBody SystemRole systemRole) {
        boolean flag = systemRoleService.save(systemRole);
        if (flag) {
            return ResponseResult.success();
        }else {
            throw new ResponseException(ResultCodeEnum.DATA_ERROR.getCode(), ResultCodeEnum.DATA_ERROR.getMessage());
        }
    }

    @Log(title = "修改角色",businessType = BusinessType.UPDATE,operatorType = OperatorType.MANAGE)
    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody SystemRole systemRole) {
        systemRoleService.updateById(systemRole);
        return ResponseResult.success();
    }

    @Log(title = "删除角色",businessType = BusinessType.DELETE,operatorType = OperatorType.MANAGE)
    @ApiOperation(value = "删除数据")
    @DeleteMapping("/delete")
    public ResponseResult delete(Long id) {
        boolean flag = systemRoleService.removeById(id);
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
        systemRoleService.removeByIds(idList);
        return ResponseResult.success();
    }
}

