package org.edu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.edu.dto.LoginParam;
import org.edu.dto.RegisterUser;
import org.edu.result.ResponseResult;
import org.edu.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author :zzd
 * @date : 2023-01-13 22:34
 */
@RestController
@Api(tags = "app登录接口")
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
    public ResponseResult register(@RequestBody RegisterUser registerUser) {
        return userService.register(registerUser);
    }
}
