package com.chengxiaoxiao.model.web.pojos;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 11:31 下午
 * @Description:
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

    @ApiModelProperty(value = "姓名", required = true)
    private String userName;
    @ApiModelProperty(value = "电话号码", required = true)
    private String telephone;
    @ApiModelProperty(value = "出生日期", required = true)
    private Date birthday;
    @ApiModelProperty(value = "电子邮箱", required = true)
    private String email;
    @ApiModelProperty(value = "性别", required = true)
    private Integer sex;
    @ApiModelProperty(value = "地址", required = true)
    private String address;
    @ApiModelProperty(value = "描述信息", required = true)
    private String descript;
    @ApiModelProperty(value = "是否锁定", required = true)
    private String locked;
    @ApiModelProperty(value = "昵称", required = true)
    private String nickName;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    @ApiModelProperty(value = "创建时间", required = true)
    private String createTime;
    @ApiModelProperty(value = "修改时间", required = true)
    private String UpdateTime;
    @ApiModelProperty(value = "删除状态", required = true)
    private String deleteStatus;
}