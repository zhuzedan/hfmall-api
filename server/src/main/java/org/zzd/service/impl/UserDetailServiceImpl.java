package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.zzd.domain.SystemUser;
import org.zzd.mapper.SystemUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author :zzd
 * @date : 2023/1/5
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    SystemUserMapper systemUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<SystemUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SystemUser::getUsername,username);
        SystemUser systemUser = systemUserMapper.selectOne(lambdaQueryWrapper);
        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(systemUser)){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        //TODO 根据用户查询权限信息 添加到LoginUser中
        // List<String> list = new ArrayList<>(Arrays.asList("test","admin"));
        //封装成UserDetails对象返回
        return new SystemUser(systemUser);
    }
}
