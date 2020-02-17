package com.chengxiaoxiao.model.web.dtos.query.sysresource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
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
@ApiModel("资源操作对象模型")
public class SysResourceModelDto {
    @NotNull(message = "请输入资源名")
    @ApiModelProperty("资源名")
    private String name;
    @NotNull(message = "请输入资源类型")
    @ApiModelProperty("资源类型")
    private Integer type;
    @NotNull(message = "请输入资源KEY")
    @ApiModelProperty("资源KEY")
    private String scourceKey;
    @NotNull(message = "请输入资源URL")
    @ApiModelProperty("资源URL")
    private String sourceUrl;
    @ApiModelProperty("资源描述信息")
    private String descript;
    @ApiModelProperty("资源图标")
    private String icon;
    @NotNull(message = "请选择是否显示")
    @ApiModelProperty("是否显示")
    private String isShow;
    @ApiModelProperty("排序")
    private Integer sort;
    @ApiModelProperty("父ID")
    private String parentId;
}
