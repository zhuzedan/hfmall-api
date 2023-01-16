package org.edu.controller;

import org.edu.domain.SystemRole;
import org.edu.service.SystemRoleService;
import org.edu.vo.RespPageBean;
import org.edu.result.ResponseResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;

/**
 * 角色(SystemRole)表控制层
 *
 * @author zzd
 * @since 2023-01-15 23:46:44
 */
@Api(tags = "RBAC_角色管理接口")
@RestController
@RequestMapping("/api/systemRole")
public class SystemRoleController {

    @Autowired
    private SystemRoleService systemRoleService;

    @ApiOperation(value = "分页查询")
    @PostMapping("/queryPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", dataType = "integer",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", paramType = "query", dataType = "integer",defaultValue = "10"),
            @ApiImplicitParam(name = "roleName", value = "角色名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "startCreateTime", value = "起始日期", paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "endCreateTime", value = "结束日期", paramType = "query", dataType = "date"),
    })
    public ResponseResult<RespPageBean<SystemRole>> queryPage(@ApiIgnore @RequestParam HashMap params) {
        return systemRoleService.queryPage(params);
    }

    @ApiOperation(value = "获取详情")
    @GetMapping("/read")
    public ResponseResult selectOne(Integer id) {
        SystemRole systemRole = systemRoleService.getById(id);
        return ResponseResult.success(systemRole);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/save")
    public ResponseResult insert(@RequestBody SystemRole systemRole) {
        boolean flag = systemRoleService.save(systemRole);
        if (flag) {
            return ResponseResult.success();
        }else {
            return ResponseResult.error();
        }
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody SystemRole systemRole) {
        systemRoleService.updateById(systemRole);
        return ResponseResult.success();
    }

    @ApiOperation(value = "删除数据")
    @DeleteMapping("/delete")
    public ResponseResult delete(Long id) {
        boolean flag = systemRoleService.removeById(id);
        if (flag) {
            return ResponseResult.success();
        }
        else {
            return ResponseResult.error();
        }
    }
    @ApiOperation(value = "批量删除数据")
    @DeleteMapping("/batchRemove")
    public ResponseResult batchRemove(@RequestBody List<Long> idList) {
        systemRoleService.removeByIds(idList);
        return new  ResponseResult(200,"批量删除成功");
    }
}

