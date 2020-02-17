package com.chengxiaoxiao.model.web.dtos.query.sysresource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;

/**
 * 资源信息查询条件
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/16 10:12 上午
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("资源搜索条件")
public class SysResourceSearchDto {
    @ApiModelProperty("资源名")
    private String name;
    @ApiModelProperty("资源类型")
    private Integer type;
    @ApiModelProperty("资源Key")
    private String scourceKey;
    @ApiModelProperty("资源URL")
    private String sourceUrl;
    @ApiModelProperty("资源描述信息")
    private String descript;
    @ApiModelProperty("是否显示")
    private String isShow;
    @ApiModelProperty("资源父Id")
    private String parentId;
    @ApiModelProperty("创建人")
    private String createUser;
    @ApiModelProperty(value="创建时间-开始时间",example="2010-10-24 12:12:13")
    private Date startCreateTime;
    @ApiModelProperty("创建时间-结束时间")
    private Date endCreateTime;
    @ApiModelProperty("删除状态")
    private Integer deleteStatus;

}
