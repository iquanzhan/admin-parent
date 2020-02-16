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
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/15 9:57 下午
 * @Description:
 */
@Data
@Entity
@Table(name = "sys_role")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("角色实体")
public class SysRole {
    @Id
    @ApiModelProperty("角色Id")
    private String id;
    @ApiModelProperty("角色KEY")
    private String roleKey;
    @ApiModelProperty("角色名称")
    private String name;
    @ApiModelProperty("角色描述")
    private String descript;
    @ApiModelProperty("角色父Id")
    private String parentId;
    @ApiModelProperty("角色排序")
    private Integer sort;
    @ApiModelProperty("创建人")
    private String createUser;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新人")
    private String updateUser;
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty(value="删除状态",notes = "0代表正常，1代表已删除")
    private Integer deleteStatus;
}
