package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.zzd.constant.PageConstant;
import org.zzd.constant.SecurityConstants;
import org.zzd.domain.SystemUser;
import org.zzd.dto.*;
import org.zzd.exception.ResponseException;
import org.zzd.mapper.SystemUserMapper;
import org.zzd.result.ResponseResult;
import org.zzd.result.ResultCodeEnum;
import org.zzd.service.SystemMenuService;
import org.zzd.service.SystemUserService;
import org.zzd.utils.JwtTokenUtil;
import org.zzd.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
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
    @Autowired
    SystemUserMapper systemUserMapper;
    @Autowired
    SystemMenuService systemMenuService;
    @Resource
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 用户登录
     * @param loginParam
     * @return
     */
    @Override
    public ResponseResult login(LoginParam loginParam) {
        //使用ProviderManager auth方法进行验证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginParam.getUsername(),loginParam.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //校验失败了
        if(Objects.isNull(authenticate)){
            throw new ResponseException(ResultCodeEnum.LOGIN_ERROR.getCode(), ResultCodeEnum.LOGIN_ERROR.getMessage());
        }
        //生成自己jwt给前端
        SystemUser loginUser = (SystemUser) authenticate.getPrincipal();
        String userId = loginUser.getId().toString();
        //状态为1才能登录
            String jwt = JwtTokenUtil.createJWT(userId);
            Map<String,String> map = new HashMap();
            map.put("token",jwt);
            map.put("tokenHead", SecurityConstants.TOKEN_PREFIX);

            return ResponseResult.success("登录成功",map);

    }

    /**
     * 获取当前用户信息
     * @return
     */
    @Override
    public ResponseResult getInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SystemUser loginUser = (SystemUser) authentication.getPrincipal();
        SystemUser systemUser = systemUserMapper.selectOne(new QueryWrapper<SystemUser>().eq("id", loginUser.getId()));
        systemUser.setPassword(null);
        Map<String,Object> map = new HashMap<>();
        map.put("userInfo",systemUser);
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

    /**
     * 用户分页查询
     * @param params
     * @return
     */
    @Override
    public ResponseResult<PageHelper<SystemUser>> queryPage(HashMap params) {
        int pageNum = Integer.parseInt((String) params.get(PageConstant.PAGE_NUM));
        int pageSize = Integer.parseInt((String) params.get(PageConstant.PAGE_SIZE));
        Page<SystemUser> page = new Page(pageNum, pageSize);

        LambdaQueryWrapper<SystemUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //用户名
        if (!StringUtils.isBlank((CharSequence) params.get("username"))) {
            lambdaQueryWrapper.like(SystemUser::getUsername,params.get("username"));
        }
        //手机号
        if (!StringUtils.isBlank((CharSequence) params.get("phone"))) {
            lambdaQueryWrapper.eq(SystemUser::getPhone,params.get("phone"));
        }
        // 起始日期
        if(!StringUtils.isBlank((CharSequence) params.get("startCreateTime"))){
            lambdaQueryWrapper.ge(SystemUser::getCreateTime,params.get("startCreateTime"));
        }
        // 结束日期
        if(!StringUtils.isBlank((CharSequence) params.get("endCreateTime"))){
            lambdaQueryWrapper.le(SystemUser::getCreateTime,params.get("endCreateTime"));
        }

        IPage<SystemUser> iPage = this.page(page, lambdaQueryWrapper);
        return ResponseResult.success(PageHelper.restPage(iPage));
    }

    /**
     * 更改用户状态
     * @param params
     * @return
     */
    @Override
    public ResponseResult updateStatus(HashMap params) {
        SystemUser systemUser = baseMapper.selectById((Serializable) params.get("id"));
        if (systemUser.getId()==1) {
            throw new ResponseException(ResultCodeEnum.NO_PERMISSION.getCode(), ResultCodeEnum.NO_PERMISSION.getMessage());
        }else {
            baseMapper.updateById(systemUser);
            System.out.println(baseMapper.updateById(systemUser));
            return ResponseResult.success();
        }
    }

    /**
     * 新增用户
     * @param systemUser
     * @return
     */
    @Override
    public ResponseResult createUser(SystemUser systemUser) {
        Long count = systemUserMapper.selectCount(new QueryWrapper<SystemUser>().eq("username", systemUser.getUsername()));
        if (count > 0) {
            throw new ResponseException(ResultCodeEnum.USER_ACCOUNT_ALREADY_EXIST.getCode(), ResultCodeEnum.USER_ACCOUNT_ALREADY_EXIST.getMessage());
        }
        systemUser.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        systemUserMapper.insert(systemUser);
        return ResponseResult.success();
    }
}

