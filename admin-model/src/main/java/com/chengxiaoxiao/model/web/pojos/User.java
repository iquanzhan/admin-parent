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
@Table(name = "sys_user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    private String id;
    private String userName;
    private String telephone;
    private Date birthday;

    private String email;
    private Integer sex;
    private String address;

    private String descript;

    @Column(name="isLocked")
    private Integer locked;

    private String nickName;

    private String password;

    private Date createTime;

    private Date UpdateTime;

    private Integer deleteStatus;
}