package com.chengxiaoxiao.web.security;


import com.chengxiaoxiao.common.utils.ResultUtil;
import com.chengxiaoxiao.model.common.dtos.result.CodeMsg;
import com.chengxiaoxiao.model.common.dtos.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理类
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 8:38 下午
 * @Description:
 */
@Slf4j
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        // 这些对于操作的处理类可以根据不同异常进行不同处理
        if (exception instanceof UsernameNotFoundException) {
            log.info("【登录失败】" + exception.getMessage());
            ResultUtil.responseJson(response, Result.error(CodeMsg.USER_NOT_EXIST));
        }
        if (exception instanceof LockedException) {
            log.info("【登录失败】" + exception.getMessage());
            ResultUtil.responseJson(response, Result.error(CodeMsg.USER_LOCKED));
        }
        if (exception instanceof BadCredentialsException) {
            log.info("【登录失败】" + exception.getMessage());
            ResultUtil.responseJson(response, Result.error(CodeMsg.USER_PASSWORD_INCORRENT));
        }
        ResultUtil.responseJson(response, Result.error(CodeMsg.ERROR));
    }
}
