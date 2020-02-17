package com.chengxiaoxiao.model.web.dtos.query.sysuser;

import com.chengxiaoxiao.model.annotation.validator.IsMobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * 用户新增添加时使用的MODEL
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/6 8:29 下午
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户操作对象模型")
public class SysUserModelDto {

    @NotBlank(message = "请输入正确的用户名")
    @ApiModelProperty(value = "用户名", required = true)
    private String userName;

    @NotNull
    @ApiModelProperty(value = "昵称", required = true)
    private String nickName;

    @NotNull
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @IsMobile(message = "请输入正确的手机号码")
    @ApiModelProperty(value = "手机号码", required = true)
    private String telephone;

    @Past(message = "请输入正确的出生日期")
    @ApiModelProperty(value = "出生日期", required = true)
    private Date birthday;

    @Email(message = "请输入正确的电子邮箱")
    @ApiModelProperty(value = "电子邮箱", required = true)
    private String email;

    @NotNull(message = "请输入正确的性别信息")
    @ApiModelProperty(value = "性别", required = true)
    private Integer sex;

    @ApiModelProperty(value = "地址", required = true)
    private String address;

    @ApiModelProperty(value = "描述信息", required = true)
    private String descript;
}
