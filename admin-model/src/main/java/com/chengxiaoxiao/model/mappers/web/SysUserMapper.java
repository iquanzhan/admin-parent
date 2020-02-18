package com.chengxiaoxiao.model.mappers.web;

import com.chengxiaoxiao.model.web.pojos.SysUser;

import java.util.List;

/**
 * userMapper
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/18 4:09 下午
 * @Description:
 */
public interface SysUserMapper {
    /**
     * 根据角色Id查询用户列表
     *
     * @param roleId
     * @return
     */
    List<SysUser> findUsersByRoleId(String roleId);
}
