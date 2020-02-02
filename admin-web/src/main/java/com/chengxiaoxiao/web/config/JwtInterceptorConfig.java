package com.chengxiaoxiao.web.config;

import com.chengxiaoxiao.web.interceptor.JwtIntercptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 7:34 下午
 * @Description:
 */
@Configuration
public class JwtInterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtIntercptor jwtIntercptor;
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtIntercptor).addPathPatterns("/**").excludePathPatterns("/login");
    }
}

