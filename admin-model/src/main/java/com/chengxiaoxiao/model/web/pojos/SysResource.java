package com.chengxiaoxiao.model.web.pojos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 系统资源实体
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/16 3:14 下午
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_resource")
@ApiModel("资源结果实体类")
public class SysResource {
    @Id
    @ApiModelProperty("资源Id")
    private String id;
    @ApiModelProperty("资源名称")
    private String name;
    @ApiModelProperty("资源类型")
    private Integer type;
    @ApiModelProperty("资源KEY")
    private String scourceKey;
    @ApiModelProperty("请求方法")
    private String httpMethod;
    @ApiModelProperty("资源URL")
    private String sourceUrl;
    @ApiModelProperty("资源描述")
    private String descript;
    @ApiModelProperty("资源图标")
    private String icon;
    @ApiModelProperty("是否展示")
    private Integer isShow;
    @ApiModelProperty("排序")
    private Integer sort;
    @ApiModelProperty("父Id")
    private String parentId;
    @ApiModelProperty("创建用户")
    private String createUser;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新用户")
    private String updateUser;
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("删除状态")
    private Integer deleteStatus;
}
