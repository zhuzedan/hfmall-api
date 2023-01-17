package org.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.edu.constants.SecurityConstants;
import org.edu.domain.SystemUser;
import org.edu.dto.LoginParam;
import org.edu.dto.LoginUser;
import org.edu.dto.RegisterUser;
import org.edu.dto.SecurityLoginUser;
import org.edu.exception.BusinessException;
import org.edu.mapper.SystemUserMapper;
import org.edu.result.ResponseResult;
import org.edu.result.ResultCodeEnum;
import org.edu.service.SystemMenuService;
import org.edu.service.SystemUserService;
import org.edu.utils.JwtUtil;
import org.edu.vo.RouterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
    @Autowired
    SystemUserMapper systemUserMapper;
    @Autowired
    SystemMenuService systemMenuService;
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
        //状态为1才能登录
        Integer status = loginUser.getSystemUser().getStatus();
        if (status == 1) {
            String jwt = JwtUtil.createJWT(userId);
            Map<String,String> map = new HashMap();
            map.put("token",jwt);
            map.put("tokenHead",SecurityConstants.TOKEN_PREFIX);

            return ResponseResult.success("登录成功",map);
        }
        else {
            throw new BusinessException(ResultCodeEnum.ACCOUNT_STOP.getCode(),ResultCodeEnum.ACCOUNT_STOP.getMessage());
        }

    }

    /**
     * 获取当前用户信息
     * @return
     */
    @Override
    public ResponseResult getInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityLoginUser loginUser = (SecurityLoginUser) authentication.getPrincipal();
        SystemUser systemUser = systemUserMapper.selectOne(new QueryWrapper<SystemUser>().eq("id", loginUser.getId()));
        systemUser.setPassword(null);
        //查询菜单权限值
        List<RouterVo> routerVoList = systemMenuService.getUserMenuList(systemUser.getId());
        //查询按钮权限值
        List<String> permsList = systemMenuService.getUserButtonList(systemUser.getId());

        Map<String,Object> map = new HashMap<>();
        map.put("userInfo",systemUser);
        map.put("routers",routerVoList);
        map.put("buttons",permsList);
        return ResponseResult.success(map);
    }

    /**
     * app用户注册
     * @param registerUser
     * @return
     */
    @Override
    public ResponseResult register(RegisterUser registerUser) {
        SystemUser user = new SystemUser(registerUser);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String bCryptPassword = bCryptPasswordEncoder.encode(registerUser.getPassword());
        user.setPassword(bCryptPassword);
        user.setIsAppuser(1);
        systemUserMapper.insert(user);
        return ResponseResult.success();
    }
}

