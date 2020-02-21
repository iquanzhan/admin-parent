package com.chengxiaoxiao.web.security.service;

import com.chengxiaoxiao.model.web.dtos.UserEntitySecurity;
import com.chengxiaoxiao.model.web.pojos.SysUser;
import com.chengxiaoxiao.web.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * SpringSecurity用户的业务实现
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 8:39 下午
 * @Description:
 */
@Service
public class SelfUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserEntitySecurity loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        SysUser sysUserEntity = sysUserService.selectUserByName(username);
        if (sysUserEntity != null) {
            // 组装参数
            UserEntitySecurity selfUserEntity = new UserEntitySecurity();
            BeanUtils.copyProperties(sysUserEntity, selfUserEntity);
            return selfUserEntity;
        }
        return null;
    }
}