package org.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.edu.domain.User;
import org.edu.dto.LoginParam;
import org.edu.dto.RegisterUser;
import org.edu.result.ResponseResult;
import org.edu.vo.RespPageBean;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 前台用户登录(User)表服务接口
 *
 * @author zzd
 * @since 2023-01-13 22:53:34
 */
public interface UserService extends IService<User> {

    ResponseResult login(LoginParam loginParam);

    ResponseResult getInfo(HttpServletRequest request);

    ResponseResult register(RegisterUser registerUser);
}

