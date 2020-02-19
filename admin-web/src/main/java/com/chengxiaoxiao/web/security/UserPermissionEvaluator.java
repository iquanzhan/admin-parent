package com.chengxiaoxiao.web.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
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

    /**
     * hasPermission鉴权方法
     * 这里仅仅判断PreAuthorize注解中的权限表达式
     * 实际中可以根据业务需求设计数据库通过targetUrl和permission做更复杂鉴权
     *
     * @Author Sans
     * @CreateTime 2019/10/6 18:25
     * @Param authentication  用户身份
     * @Param targetUrl  请求路径
     * @Param permission 请求路径权限
     * @Return boolean 是否通过
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
