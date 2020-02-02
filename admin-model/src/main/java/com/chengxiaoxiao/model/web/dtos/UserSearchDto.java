package com.chengxiaoxiao.model.web.dtos;

import com.chengxiaoxiao.model.web.pojos.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 1:31 下午
 * @Description:
 */
@ApiModel("用户搜索条件")
@Data
public class UserSearchDto {

    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty(value = "开始时间", dataType = "date")
    private Date startTime;
    @ApiModelProperty("结束时间")
    private Date endTime;
}
