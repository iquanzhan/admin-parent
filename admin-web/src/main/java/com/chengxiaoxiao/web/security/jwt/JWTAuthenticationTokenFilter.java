package com.chengxiaoxiao.web.security.jwt;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 8:40 下午
 * @Description:
 */

import com.alibaba.fastjson.JSONObject;
import com.chengxiaoxiao.common.config.JwtConfig;
import com.chengxiaoxiao.common.jwt.JwtUtil;
import com.chengxiaoxiao.common.utils.ResultUtil;
import com.chengxiaoxiao.model.common.dtos.result.CodeMsg;
import com.chengxiaoxiao.model.common.dtos.result.Result;
import com.chengxiaoxiao.model.web.dtos.UserEntitySecurity;
import com.chengxiaoxiao.web.exception.GlobleException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * JWT接口请求校验拦截器
 * 请求接口时会进入这里验证Token是否合法和过期
 *
 * @Author Sans
 * @CreateTime 2019/10/5 16:41
 */
@Slf4j
@Component
public class JWTAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    JwtConfig jwtConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中JWT的Token
        String tokenHeaderAuth = request.getHeader(jwtConfig.getTokenHeader());

        try {
            if (null != tokenHeaderAuth && tokenHeaderAuth.startsWith(jwtConfig.getTokenPrefix())) {
                // 截取JWT前缀
                String token = tokenHeaderAuth.replace(jwtConfig.getTokenPrefix(), "");
                Claims claims = jwtUtil.parseJWT(token);

                // 获取用户名
                String username = claims.getSubject();
                String userId = claims.getId();
                if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(userId) && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // 获取角色
                    Set<GrantedAuthority> authorities = new HashSet<>();
                    String authority = claims.get("authorities").toString();
                    if (!StringUtils.isEmpty(authority)) {
                        Set<String> authSet = JSONObject.parseObject(authority, Set.class);
                        for (String s : authSet) {
                            authorities.add(new SimpleGrantedAuthority(s));
                        }
                    }

                    //组装参数
                    UserEntitySecurity selfUserEntity = new UserEntitySecurity();
                    selfUserEntity.setUserName(claims.getSubject());
                    selfUserEntity.setId(userId);
                    selfUserEntity.setAuthorities(authorities);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(selfUserEntity, null, selfUserEntity.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (ExpiredJwtException e) {
            ResultUtil.responseJson(response, Result.success(CodeMsg.AUTHENTICATION_TOKEN_EXPIRED));
        } catch (Exception e) {
            ResultUtil.responseJson(response, Result.success(CodeMsg.AUTHENTICATION_ERROR));
        }
        filterChain.doFilter(request, response);
        return;
    }

}