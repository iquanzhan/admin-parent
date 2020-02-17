package com.chengxiaoxiao.model.web.dtos.query.sysuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 用户登录模型
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/17 3:39 下午
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户登录模型")
public class SysLoginModelDto {
    @NotNull(message = "请输入用户名")
    @ApiModelProperty(value = "用户名", required = true)
    private String userName;

    @NotNull(message = "请输入密码")
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
