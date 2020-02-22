package com.chengxiaoxiao.model.web.dtos.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/18 2:14 下午
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("角色信息")
@Entity
public class SysRoleSimpleDtos {
    @ApiModelProperty("角色Id")
    private String id;
    @ApiModelProperty("角色名称")
    private String name;
    @ApiModelProperty("角色描述")
    private String descript;
    @ApiModelProperty("角色KEY")
    private String roleKey;
}
