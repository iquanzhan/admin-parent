package com.chengxiaoxiao.model.repository;

import com.chengxiaoxiao.model.web.pojos.SysResource;
import com.chengxiaoxiao.model.web.pojos.SysUserRole;

/**
 * 用户角色关联表
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/15 10:04 下午
 * @Description:
 */
public interface SysUserRoleRepository extends BaseDao<SysUserRole, String> {
    /**
     * 根据用户Id删除信息
     *
     * @param userId
     */
    void deleteByUserId(String userId);

    /**
     * 根据角色Id删除信息
     *
     * @param roleId
     */
    void deleteByRoleId(String roleId);
}
