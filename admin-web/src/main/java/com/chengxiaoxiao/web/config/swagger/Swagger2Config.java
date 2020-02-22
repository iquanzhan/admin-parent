package com.chengxiaoxiao.web.config.swagger;

import com.chengxiaoxiao.common.config.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger 配置
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 11:23 下午
 * @Description:
 */
@Configuration
@EnableSwagger2
@Profile({"dev", "test"})// 设置 dev test 环境开启 prod 环境就关闭了
public class Swagger2Config {
    @Autowired
    JwtConfig jwtConfig;

    @Bean
    public Docket createRestApi() {
        //添加head参数start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name(jwtConfig.getTokenHeader())
                .description("TOKEN")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true).build();
        pars.add(tokenPar.build());
        //添加head参数end

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.chengxiaoxiao.web.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档标题")
                .description("接口文档描述")
//                .termsOfServiceUrl("http:/xxx/xxx")
                .contact("作者")
                .version("1.0")
                .build();
    }
}