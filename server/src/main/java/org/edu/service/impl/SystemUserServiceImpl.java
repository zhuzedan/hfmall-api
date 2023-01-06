package org.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.edu.constants.SecurityConstants;
import org.edu.domain.SystemUser;
import org.edu.dto.LoginParam;
import org.edu.dto.LoginUser;
import org.edu.exception.BusinessException;
import org.edu.mapper.SystemUserMapper;
import org.edu.result.ResponseResult;
import org.edu.result.ResultCodeEnum;
import org.edu.service.SystemUserService;
import org.edu.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 用户表(SystemUser)表服务实现类
 *
 * @author zzd
 * @since 2023-01-05 23:10:13
 */
@Service("systemUserService")
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    public ResponseResult login(LoginParam loginParam) {
        //使用ProviderManager auth方法进行验证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginParam.getUsername(),loginParam.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //校验失败了
        if(Objects.isNull(authenticate)){
            throw new BusinessException(ResultCodeEnum.LOGIN_ERROR.getCode(), ResultCodeEnum.LOGIN_ERROR.getMessage());
        }
        //生成自己jwt给前端
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getSystemUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String,String> map = new HashMap();
        map.put("token",jwt);
        map.put("tokenHead",SecurityConstants.TOKEN_PREFIX);

        return ResponseResult.success("登录成功",map);
    }
}

