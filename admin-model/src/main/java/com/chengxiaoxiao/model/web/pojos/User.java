package com.chengxiaoxiao.model.web.pojos;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 11:31 下午
 * @Description:
 */

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
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 10:35 下午
 * @Description:
 */
@Data
@Entity
@Table(name = "SYS_USER")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户对象模型")
public class User implements Serializable {

    @Id
    @ApiModelProperty(value = "用户Id", required = true)
    private String id;

    @NotBlank(message = "请输入正确的用户名")
    @ApiModelProperty(value = "用户名", required = true)
    private String userName;

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

    @NotNull(message = "请输入正确的性别信息")
    @ApiModelProperty(value = "是否锁定", required = true)
    private Integer locked;

    @NotNull
    @ApiModelProperty(value = "昵称", required = true)
    private String nickName;

    @NotNull
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @NotNull
    @ApiModelProperty(value = "创建时间", required = true)
    private String createTime;

    @NotNull
    @ApiModelProperty(value = "修改时间", required = true)
    private String UpdateTime;

    @NotNull
    @ApiModelProperty(value = "删除状态", required = true)
    private Integer deleteStatus;
}