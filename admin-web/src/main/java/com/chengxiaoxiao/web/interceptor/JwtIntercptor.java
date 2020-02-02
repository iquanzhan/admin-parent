package com.chengxiaoxiao.web.interceptor;

import com.chengxiaoxiao.common.jwt.JwtUtil;
import com.chengxiaoxiao.model.common.dtos.result.CodeMsg;
import com.chengxiaoxiao.web.exception.GlobleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 7:18 下午
 * @Description:
 */
@Component
public class JwtIntercptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("X-Token");
        if (token != null && !"".equals(token)) {
            try {
                Claims claims = jwtUtil.parseJWT(token);
                String role = (String) claims.get("roles");

                if (role != null && role.equals("admin")) {
                    request.setAttribute("claims_admin", token);
                }
            } catch (Exception e) {
                throw new GlobleException(CodeMsg.AUTHENTICATION_ERROR);
            }
        }

        return true;
    }
}

