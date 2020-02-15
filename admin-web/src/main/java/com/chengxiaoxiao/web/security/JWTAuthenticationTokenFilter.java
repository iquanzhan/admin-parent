package com.chengxiaoxiao.web.security;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 8:40 下午
 * @Description:
 */

import com.alibaba.fastjson.JSONObject;
import com.chengxiaoxiao.model.common.dtos.result.CodeMsg;
import com.chengxiaoxiao.web.exception.GlobleException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * JWT接口请求校验拦截器
 * 请求接口时会进入这里验证Token是否合法和过期
 *
 * @Author Sans
 * @CreateTime 2019/10/5 16:41
 */
@Slf4j
public class JWTAuthenticationTokenFilter extends BasicAuthenticationFilter {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;

    @Value("${jwt.expiration}")
    private String expiration;


    public JWTAuthenticationTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        // 获取请求头中JWT的Token
//        String tokenHeader = request.getHeader(this.tokenHeader);
//        if (null != tokenHeader && tokenHeader.startsWith(tokenPrefix)) {
//            try {
//                // 截取JWT前缀
//                String token = tokenHeader.replace(tokenPrefix, "");
//                // 解析JWT
//                Claims claims = Jwts.parser()
//                        .setSigningKey(secret)
//                        .parseClaimsJws(token)
//                        .getBody();
//                // 获取用户名
//                String username = claims.getSubject();
//                String userId = claims.getId();
//                if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(userId)) {
//                    // 获取角色
//                    List<GrantedAuthority> authorities = new ArrayList<>();
//                    String authority = claims.get("authorities").toString();
//                    if (!StringUtils.isEmpty(authority)) {
//                        List<Map<String, String>> authorityMap = JSONObject.parseObject(authority, List.class);
//                        for (Map<String, String> role : authorityMap) {
//                            if (!StringUtils.isEmpty(role)) {
//                                authorities.add(new SimpleGrantedAuthority(role.get("authority")));
//                            }
//                        }
//                    }
//                    //组装参数
//                    SelfUserEntity selfUserEntity = new SelfUserEntity();
//                    selfUserEntity.setUsername(claims.getSubject());
//                    selfUserEntity.setUserId(Long.parseLong(claims.getId()));
//                    selfUserEntity.setAuthorities(authorities);
//                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(selfUserEntity, userId, authorities);
//
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            } catch (ExpiredJwtException e) {
//                throw new GlobleException(CodeMsg.AUTHENTICATION_TOKEN_EXPIRED);
//            } catch (Exception e) {
//                throw new GlobleException(CodeMsg.AUTHENTICATION_ERROR);
//            }
//        }
        filterChain.doFilter(request, response);
        return;
    }
}