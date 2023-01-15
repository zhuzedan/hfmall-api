package org.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.edu.domain.SystemUser;
import org.edu.dto.LoginParam;
import org.edu.dto.RegisterUser;
import org.edu.result.ResponseResult;


/**
 * 用户表(SystemUser)表服务接口
 *
 * @author zzd
 * @since 2023-01-05 23:10:13
 */
public interface SystemUserService extends IService<SystemUser> {

    ResponseResult login(LoginParam loginParam);


    ResponseResult getInfo();

    //app用户注册
    ResponseResult register(RegisterUser registerUser);
}

