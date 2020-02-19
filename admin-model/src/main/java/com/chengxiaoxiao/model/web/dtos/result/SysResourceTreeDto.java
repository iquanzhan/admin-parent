package com.chengxiaoxiao.model.web.dtos.result;

import com.chengxiaoxiao.model.web.pojos.SysResource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * 资源树形显示
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/19 4:25 下午
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("系统资源树形显示模型")
public class SysResourceTreeDto {

    @ApiModelProperty("资源Id")
    private String id;
    @ApiModelProperty("资源名称")
    private String name;
    @ApiModelProperty(value = "资源类型", notes = " 目录：1，菜单：2，按钮：3")
    private Integer type;
    @ApiModelProperty("资源KEY")
    private String scourceKey;
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
    @ApiModelProperty("子元素")
    private List<SysResourceTreeDto> children;
}
