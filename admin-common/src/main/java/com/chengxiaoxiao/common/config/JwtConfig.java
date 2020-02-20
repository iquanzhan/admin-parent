package com.chengxiaoxiao.common.config;

import lombok.Data;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * jwt配置类，读取配置文件信息
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/20 10:24 下午
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private String secret;
    private String tokenHeader;
    private String tokenPrefix;
    private Integer expiration;
    private String antMatchers;
}
