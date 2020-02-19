package com.chengxiaoxiao.model.mappers.web;

import com.chengxiaoxiao.model.web.dtos.result.SysRoleSimpleDtos;
import com.chengxiaoxiao.model.web.dtos.result.SysRoleTreeDto;
import com.chengxiaoxiao.model.web.pojos.SysResource;
import com.chengxiaoxiao.model.web.pojos.SysRoleResource;
import com.chengxiaoxiao.model.web.pojos.SysUser;

import java.util.List;

/**
 * 角色资源关联表
 * SysRoleResourceMapper
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/18 4:09 下午
 * @Description:
 */
public interface SysRoleResourceMapper {
    /**
     * 批量添加数据
     *
     * @param list
     * @return
     */
    int batchInsert(List<SysRoleResource> list);

    /**
     * 根据角色Id获取资源列表
     *
     * @param roleId
     * @return
     */
    List<SysResource> finResourceByRoleId(String roleId);

    /**
     * 根据roleId数组获取资源信息
     * @param roles
     * @return
     */
    List<SysResource> findResourcesByRoles(List<SysRoleSimpleDtos> roles);
}
