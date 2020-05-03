package com.chengxiaoxiao.user.security.filter;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/21 11:00 下午
 * @Description:
 */
@Getter
@Setter
public class AuthenticationBean {
    private String username;
    private String password;
}
