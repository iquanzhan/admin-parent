package com.chengxiaoxiao.model.common.dtos.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 1:18 下午
 * @Description:
 */
@Data
@ApiModel("分页排序参数")
public class PageQueryDtos {
    @ApiModelProperty(value = "页码",notes = "不传此参数，默认第一页")
    private Integer page;
    @ApiModelProperty(value="页码",notes = "不传此参数，默认显示10条")
    private Integer size;
    @ApiModelProperty(value="排序",notes = "不传此参数，按照默认排序规则")
    private String sort;
}
