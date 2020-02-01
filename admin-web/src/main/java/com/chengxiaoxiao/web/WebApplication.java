package com.chengxiaoxiao.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动类
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 9:40 下午
 * @Description:
 */
@SpringBootApplication
@EnableSwagger2
@EntityScan("com.chengxiaoxiao.model.web.pojos")
@EnableJpaRepositories("com.chengxiaoxiao.model.repository")
public class WebApplication  {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    //extends SpringBootServletInitializer
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(WebApplication.class);
//    }
}
