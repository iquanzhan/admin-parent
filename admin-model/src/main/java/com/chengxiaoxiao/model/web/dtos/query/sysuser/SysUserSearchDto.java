package com.chengxiaoxiao.model.web.dtos.query.sysuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 1:31 下午
 * @Description:
 */
@ApiModel("用户搜索条件")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserSearchDto {
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("手机号码")
    private String telephone;
    @ApiModelProperty("出生日期-开始时间")
    private Date birthdayStartTime;

    @ApiModelProperty("出生日期-结束时间")
    private Date birthdayEndTime;

    @ApiModelProperty("电子邮箱")
    private String email;
    @ApiModelProperty("性别")
    private Integer sex;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("描述")
    private String descript;
    @ApiModelProperty("是否锁定")
    private Integer locked;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("删除状态")
    private Integer deleteStatus;

    @ApiModelProperty(value = "创建时间-开始时间", dataType = "date", notes = "yyyy-mm-dd")
    private Date startCreateTime;
    @ApiModelProperty(value = "创建时间-结束时间", dataType = "date", notes = "yyyy-mm-dd")
    private Date endCreateTime;
}
