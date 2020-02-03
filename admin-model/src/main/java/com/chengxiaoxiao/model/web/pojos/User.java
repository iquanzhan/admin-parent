package com.chengxiaoxiao.model.web.pojos;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 11:31 下午
 * @Description:
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 10:35 下午
 * @Description:
 */
@Data
@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户对象模型")
public class User implements Serializable {
    @Id
    @ApiModelProperty(value = "用户Id", required = true)
    private Integer id;

    @ApiModelProperty(value = "姓名", required = true)
    private String name;
}