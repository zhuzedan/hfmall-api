package org.edu.controller;

import io.swagger.annotations.*;
import org.edu.domain.SystemUser;
import org.edu.service.SystemRoleService;
import org.edu.service.SystemUserService;
import org.edu.vo.AssignRoleVo;
import org.edu.vo.RespPageBean;
import org.edu.exception.BusinessException;
import org.edu.result.ResultCodeEnum;
import org.edu.result.ResponseResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;

/**
 * 用户表(SystemUser)表控制层
 *
 * @author zzd
 * @since 2023-01-17 19:08:49
 */
@Api(tags = "RBAC_用户管理")
@RestController
@RequestMapping("/api/systemUser")
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private SystemRoleService systemRoleService;

    @ApiOperation(value = "分页查询")
    @PostMapping("/queryPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", dataType = "integer",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", paramType = "query", dataType = "integer",defaultValue = "10"),
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "手机号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "startCreateTime", value = "起始日期", paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "endCreateTime", value = "结束日期", paramType = "query", dataType = "date")
    })
    public ResponseResult<RespPageBean<SystemUser>> queryPage(@ApiIgnore @RequestParam HashMap params) {
        return systemUserService.queryPage(params);
    }

    @ApiOperation(value = "获取详情")
    @GetMapping("/read")
    public ResponseResult selectOne(Integer id) {
        SystemUser systemUser = systemUserService.getById(id);
        return ResponseResult.success(systemUser);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/save")
    public ResponseResult insert(@RequestBody SystemUser systemUser) {
        boolean flag = systemUserService.save(systemUser);
        if (flag) {
            return ResponseResult.success();
        }else {
            throw new BusinessException(ResultCodeEnum.DATA_ERROR.getCode(), ResultCodeEnum.DATA_ERROR.getMessage());
        }
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody SystemUser systemUser) {
        systemUserService.updateById(systemUser);
        return ResponseResult.success();
    }

    @ApiOperation(value = "删除数据")
    @DeleteMapping("/delete")
    public ResponseResult delete(Long id) {
        boolean flag = systemUserService.removeById(id);
        if (flag) {
            return ResponseResult.success();
        }
        else {
            throw new BusinessException(ResultCodeEnum.PARAM_NOT_VALID.getCode(), ResultCodeEnum.PARAM_NOT_VALID.getMessage());
        }
    }
    @ApiOperation(value = "批量删除数据")
    @DeleteMapping("/batchRemove")
    public ResponseResult batchRemove(@RequestBody List<Long> idList) {
        systemUserService.removeByIds(idList);
        return new  ResponseResult(200,"批量删除成功");
    }

    @ApiOperation(value = "更改用户状态")
    @GetMapping("/updateStatus")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "status", value = "状态", paramType = "query", dataType = "integer")
    })
    public ResponseResult updateStatus(@ApiIgnore @RequestParam HashMap params) {
        return systemUserService.updateStatus(params);
    }

    @ApiOperation(value = "获取用户的角色")
    @GetMapping("/toAssign")
    public ResponseResult toAssign(Integer userId) {
        return systemRoleService.getRolesByUserId(userId);
    }
    @ApiOperation("用户分配角色")
    @PostMapping("/doAssign")
    public ResponseResult doAssign(@RequestBody AssignRoleVo assignRoleVo) {
        return systemRoleService.doAssign(assignRoleVo);
    }
}

