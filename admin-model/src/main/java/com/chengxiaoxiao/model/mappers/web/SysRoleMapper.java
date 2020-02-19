package com.chengxiaoxiao.model.mappers.web;

import com.chengxiaoxiao.model.web.dtos.result.SysRoleSimpleDtos;
import com.chengxiaoxiao.model.web.dtos.result.SysRoleTreeDto;
import com.chengxiaoxiao.model.web.pojos.SysResource;
import com.chengxiaoxiao.model.web.pojos.SysRole;
import com.chengxiaoxiao.model.web.pojos.SysRoleResource;
import com.chengxiaoxiao.model.web.pojos.SysUser;

import java.util.List;

/**
 * SysRoleMapper
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/18 4:09 下午
 * @Description:
 */
public interface SysRoleMapper {
    /**
     * 根据用户Id获取所属角色信息
     *
     * @param id
     * @return
     */
    List<SysRoleSimpleDtos> getRolesByUserId(String id);

    /**
     * 查询父Id下子元素的内容
     *
     * @param roleId
     * @return
     */
    List<SysRoleTreeDto> getRolesByParentId(String roleId);

    /**
     * 根据Id查询角色信息
     *
     * @param roleId
     * @return
     */
    SysRoleTreeDto getRoleById(String roleId);

    /**
     * 根据资源Id获取角色列表
     * @param resourceId
     * @return
     */
    List<SysRole> getRolesByResourceId(String resourceId);
}
