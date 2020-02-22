package com.chengxiaoxiao.model.web.dtos.result;

import com.chengxiaoxiao.model.web.pojos.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 包含角色信息的用户信息Model
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/22 2:15 下午
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRolesDto extends SysUser {
    private List<SysRoleSimpleDtos> roles;
}
