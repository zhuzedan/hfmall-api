package org.zzd.controller;

import org.zzd.domain.SystemMenu;
import org.zzd.result.ResponseResult;
import org.zzd.service.SystemMenuService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 菜单表(SystemMenu)表控制层
 *
 * @author zzd
 * @since 2023-01-06 22:01:51
 */
@Api(tags = "RBAC_菜单管理")
@RestController
@RequestMapping("/api/menu")
public class SystemMenuController {

    @Autowired
    private SystemMenuService systemMenuService;

    @ApiOperation(value = "获取菜单树形列表")
    @GetMapping("/findNodes")
    public ResponseResult findNodes() {
        return systemMenuService.findNodes();
    }

    @ApiOperation(value = "获取菜单详情")
    @GetMapping("/read")
    public ResponseResult selectOne(Integer id) {
        SystemMenu systemMenu = systemMenuService.getById(id);
        return ResponseResult.success(systemMenu);
    }

    @ApiOperation(value = "添加菜单")
    @PostMapping("/save")
    public ResponseResult insert(@RequestBody SystemMenu systemMenu) {
        boolean flag = systemMenuService.save(systemMenu);
        if (flag) {
            return ResponseResult.success();
        }else {
            return ResponseResult.error();
        }
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody SystemMenu systemMenu) {
        systemMenuService.updateById(systemMenu);
        return new ResponseResult(200,"成功修改");
    }

    @ApiOperation(value = "删除数据")
    @DeleteMapping("/delete")
    public ResponseResult delete(Long id) {
        return systemMenuService.removeMenuById(id);
    }

}

