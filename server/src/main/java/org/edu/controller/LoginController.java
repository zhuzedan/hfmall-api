package org.edu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.edu.dto.LoginParam;
import org.edu.result.ResponseResult;
import org.edu.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :zzd
 * @date : 2023/1/5
 */
@RestController
@Api(tags = "用户登录接口")
@RequestMapping("/api/systemuser")
public class LoginController {
    @Autowired
    SystemUserService systemUserService;
    // 登录
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginParam loginParam) {
        return systemUserService.login(loginParam);
    }
    //获取用户信息
    @ApiOperation("用户信息")
    @GetMapping("/info")
    public ResponseResult getInfo() {
        return systemUserService.getInfo();
    }
    //用户退出登录
    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public ResponseResult logout() {
        return ResponseResult.success();
    }
}
