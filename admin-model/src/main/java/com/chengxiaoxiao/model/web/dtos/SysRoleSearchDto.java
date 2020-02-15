package com.chengxiaoxiao.model.web.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 角色信息查询条件
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/15 10:12 下午
 * @Description:
 */
@Data
@ApiModel("角色搜索条件")
public class SysRoleSearchDto {

    @ApiModelProperty("角色名")
    private String name;
    @ApiModelProperty("角色Key")
    private String roleKey;
    @ApiModelProperty("角色描述")
    private String descript;
    @ApiModelProperty("角色父Id")
    private String parentId;
    @ApiModelProperty("创建人")
    private String createUser;
    @ApiModelProperty("创建时间-开始时间")
    private Date startCreateTime;
    @ApiModelProperty("创建时间-结束时间")
    private Date endCreateTime;
    @ApiModelProperty("删除状态")
    private Integer deleteStatus;
}
