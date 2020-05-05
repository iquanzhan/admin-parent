package com.chengxiaoxiao.model.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有表的父类
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/5/5 10:23 上午
 * @Description:
 */
@Data
public class BaseEntity implements Serializable {
    @TableId
    private String id;

    @TableLogic
    private Integer deleteStatus;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version
    private Integer version;
}
