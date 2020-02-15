package com.chengxiaoxiao.model.common.dtos.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 分页Model封装
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020-01-08 22:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页结果显示类")
public class PageResult<T> implements Serializable {
    @ApiModelProperty("总记录数")
    private long total;
    @ApiModelProperty("页码大小")
    private Integer pageSize;
    @ApiModelProperty("当前页")
    private Integer pageNumber;
    @ApiModelProperty("分页数据")
    private List<T> rows;

    public PageResult(Page<T> page) {
        this.total = page.getTotalElements();
        this.pageSize = page.getSize();
        this.pageNumber = page.getNumber();
        this.rows = page.getContent();
    }
}
