package com.chengxiaoxiao.web.security.provider;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.chengxiaoxiao.model.mappers.web.SysRoleMapper;
import com.chengxiaoxiao.model.mappers.web.SysRoleResourceMapper;
import com.chengxiaoxiao.model.web.dtos.result.SysRoleSimpleDtos;
import com.chengxiaoxiao.model.web.pojos.SysResource;
import com.chengxiaoxiao.model.web.pojos.SysUser;
import com.chengxiaoxiao.model.web.dtos.UserEntitySecurity;
import com.chengxiaoxiao.web.security.service.SelfUserDetailsService;
import com.chengxiaoxiao.web.service.SysRoleService;
import com.chengxiaoxiao.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户登录处理类
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 8:39 下午
 * @Description:
 */
@Component
@SuppressWarnings("all")
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SelfUserDetailsService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取表单输入中返回的用户名
        String userName = (String) authentication.getPrincipal();
        // 获取表单中输入的密码
        String password = (String) authentication.getCredentials();

        // 查询用户是否存在
        UserEntitySecurity userEntitySecurity = sysUserService.loadUserByUsername(userName);
        if (userEntitySecurity == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
        if (!new BCryptPasswordEncoder().matches(password, userEntitySecurity.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        // 还可以加一些其他信息的判断，比如用户账号已停用等判断
        if (userEntitySecurity.getDeleteStatus().equals(1)) {
            throw new LockedException("该用户已被冻结");
        }

        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        // 查询用户角色
        List<SysRoleSimpleDtos> roles = sysRoleService.getRolesByUserId(userEntitySecurity.getId());
        if (roles.size() > 0) {
            List<SysResource> auths = sysRoleResourceMapper.findResourcesByRoles(roles);

            for (SysResource auth : auths) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + auth.getScourceKey()));
            }
        }
        userEntitySecurity.setAuthorities(authorities);
        userEntitySecurity.setRoles(roles);
        // 进行登录
        return new UsernamePasswordAuthenticationToken(userEntitySecurity, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
