package com.chengxiaoxiao.model.web.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/15 9:57 下午
 * @Description:
 */
@Data
@Entity
@Table(name = "sys_role")
@NoArgsConstructor
@AllArgsConstructor
public class SysRole {
    @Id
    private String id;
    private String roleKey;
    private String name;
    private String descript;
    private String parentId;
    private Integer sort;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
    private Integer deleteStatus;
}
