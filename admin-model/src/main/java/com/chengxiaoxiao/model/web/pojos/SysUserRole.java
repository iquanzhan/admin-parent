package com.chengxiaoxiao.model.web.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户角色关联表
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/17 4:18 下午
 * @Description:
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_user_role")
public class SysUserRole {
    @Id
    private String id;
    private String userId;
    private String roleId;
}
