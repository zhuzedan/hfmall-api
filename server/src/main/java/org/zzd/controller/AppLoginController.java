package org.zzd.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.zzd.dto.LoginParam;
import org.zzd.dto.RegisterUserDto;
import org.zzd.result.ResponseResult;
import org.zzd.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :zzd
 * @date : 2023-01-13 22:34
 */
@RestController
@Api(tags = "APP登录相关")
@RequestMapping("/api/app-user")
public class AppLoginController {
    @Autowired
    SystemUserService userService;
    // 登录
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginParam loginParam) {
        return userService.login(loginParam);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ResponseResult register(@RequestBody RegisterUserDto registerUserDto) {
        return userService.register(registerUserDto);
    }
}
