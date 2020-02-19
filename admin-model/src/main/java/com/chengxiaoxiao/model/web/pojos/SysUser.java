package com.chengxiaoxiao.model.web.pojos;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 11:31 下午
 * @Description:
 */

import com.alibaba.fastjson.annotation.JSONField;
import com.chengxiaoxiao.model.annotation.validator.IsMobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 10:35 下午
 * @Description:
 */
@Data
@Entity
@Table(name = "sys_user")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户实体")
public class SysUser implements Serializable {

    @Id
    @ApiModelProperty("用户Id")
    private String id;
    @ApiModelProperty("用户名称")
    private String userName;
    @ApiModelProperty("手机哈麻")
    private String telephone;
    @ApiModelProperty("出生日期")
    private Date birthday;
    @ApiModelProperty("电子邮件")
    private String email;
    @ApiModelProperty(value = "性别", notes = "0代表女，1代表男")
    private Integer sex;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("描述")
    private String descript;
    @ApiModelProperty("是否锁定")
    @Column(name = "isLocked")
    private Integer locked;
    @ApiModelProperty("昵称")
    private String nickName;
    @JSONField(serialize = false)
    private String password;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("删除状态")
    private Integer deleteStatus;
}