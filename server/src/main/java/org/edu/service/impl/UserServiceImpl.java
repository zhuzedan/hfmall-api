package org.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.jsonwebtoken.Claims;
import org.edu.constant.PageConstant;
import org.edu.constants.SecurityConstants;
import org.edu.domain.Order;
import org.edu.domain.SystemUser;
import org.edu.dto.LoginParam;
import org.edu.dto.RegisterUser;
import org.edu.exception.BusinessException;
import org.edu.result.ResponseResult;
import org.edu.result.ResultCodeEnum;
import org.edu.utils.JwtUtil;
import org.edu.utils.MD5;
import org.edu.vo.RespPageBean;
import org.edu.domain.User;
import org.edu.service.UserService;
import org.edu.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台用户登录(User)表服务实现类
 *
 * @author zzd
 * @since 2023-01-13 22:53:34
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseResult login(LoginParam loginParam) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("username",loginParam.getUsername());
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw new BusinessException(ResultCodeEnum.LOGIN_ERROR.getCode(), ResultCodeEnum.LOGIN_ERROR.getMessage());
        }
        String md5Password = MD5.encrypt(loginParam.getPassword());
        System.out.println(md5Password);
        if (!user.getPassword().equals(md5Password)) {
            throw new BusinessException(ResultCodeEnum.PASSWORD_ERROR.getCode(), ResultCodeEnum.PASSWORD_ERROR.getMessage());
        }
        if (user.getStatus()==0) {
            throw new BusinessException(ResultCodeEnum.ACCOUNT_STOP.getCode(), ResultCodeEnum.ACCOUNT_STOP.getMessage());
        }
        String jwt = JwtUtil.createJWT(String.valueOf(user.getId()));
        Map<String,String> map = new HashMap();
        map.put("token",jwt);
        map.put("tokenHead",SecurityConstants.TOKEN_PREFIX);

        return ResponseResult.success("登录成功",map);
    }

    @Override
    public ResponseResult getInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        Claims claims = null;
        String userId = "";
        try {
            claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("id",userId);
        User user = userMapper.selectOne(wrapper);
        Map<String,String> map = new HashMap();
        map.put("userId",userId);
        return ResponseResult.success(user);
    }

    @Override
    public ResponseResult register(RegisterUser registerUser) {
        User user = new User(registerUser);
        String md5Password = MD5.encrypt(registerUser.getPassword());
        user.setPassword(md5Password);
        user.setCreateTime((int) System.currentTimeMillis());
        userMapper.insert(user);
        return ResponseResult.success();
    }

}

