package com.chengxiaoxiao.model.web.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 添加或者更新时的MODEL
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/15 10:19 下午
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("角色操作对象模型")
public class SysRoleModelDto {
    @ApiModelProperty("角色名")
    private String name;
    @ApiModelProperty("角色Key")
    private String roleKey;
    @ApiModelProperty("角色描述")
    private String descript;
    @ApiModelProperty("角色父Id")
    private String parentId;
    @ApiModelProperty("排序")
    private Integer sort;
}
