package com.chengxiaoxiao.web.security.evaluator;


import com.chengxiaoxiao.model.mappers.web.SysRoleMapper;
import com.chengxiaoxiao.model.mappers.web.SysRoleResourceMapper;
import com.chengxiaoxiao.model.web.dtos.UserEntitySecurity;
import com.chengxiaoxiao.model.web.dtos.result.SysRoleSimpleDtos;
import com.chengxiaoxiao.model.web.pojos.SysResource;
import com.chengxiaoxiao.web.service.SysRoleService;
import com.chengxiaoxiao.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义权限注解验证
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 8:39 下午
 * @Description:
 */
@Component
public class UserPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;

    /**
     * hasPermission 进行更复杂的鉴权
     *
     * @param authentication 用户认证对象
     * @param targetUrl      目标url
     * @param permission     所拥有的的权限
     * @return 是否验证通过
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        // 获取用户信息
        UserEntitySecurity selfUserEntity = (UserEntitySecurity) authentication.getPrincipal();
        // 查询用户权限(这里可以将权限放入缓存中提升效率)
        // 查询用户角色
        List<SysRoleSimpleDtos> roles = sysRoleService.getRolesByUserId(selfUserEntity.getId());
        if (roles.size() > 0) {
            List<SysResource> auths = sysRoleResourceMapper.findResourcesByRoles(roles);

            Set<String> permissions = new HashSet<>();
            for (SysResource auth : auths) {
                permissions.add(auth.getScourceKey());
            }
            // 权限对比
            if (permissions.contains(permission.toString())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}

