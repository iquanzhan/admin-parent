package com.chengxiaoxiao.user.security.handler;


import com.alibaba.fastjson.JSON;
import com.chengxiaoxiao.common.config.JwtConfig;
import com.chengxiaoxiao.common.jwt.JwtUtil;
import com.chengxiaoxiao.common.utils.ResultUtil;
import com.chengxiaoxiao.model.common.dtos.result.Result;
import com.chengxiaoxiao.user.security.entity.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * 登录成功处理类
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 8:38 下午
 * @Description:
 */
@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtConfig jwtConfig;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        SecurityUser userEntitySecurity = (SecurityUser) authentication.getPrincipal();
        Set<String> authoritys = new HashSet<>();
        for (GrantedAuthority authority : userEntitySecurity.getAuthorities()) {
            authoritys.add(authority.getAuthority());
        }

        String jwt = jwtConfig.getTokenPrefix() + jwtUtil.createJWT(userEntitySecurity.getCurrentUserInfo().getId(), userEntitySecurity.getCurrentUserInfo().getUsername(), JSON.toJSONString(authoritys));
        ResultUtil.responseJson(response, Result.success(jwt));
    }
}