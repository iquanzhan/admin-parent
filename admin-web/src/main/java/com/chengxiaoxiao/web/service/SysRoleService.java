package com.chengxiaoxiao.web.service;

import com.chengxiaoxiao.model.common.dtos.result.Result;
import com.chengxiaoxiao.model.web.dtos.query.sysrole.SysRoleModelDto;
import com.chengxiaoxiao.model.web.dtos.query.sysrole.SysRoleSearchDto;
import com.chengxiaoxiao.model.web.dtos.result.SysRoleSimpleDtos;
import com.chengxiaoxiao.model.web.dtos.result.SysRoleTreeDto;
import com.chengxiaoxiao.model.web.pojos.SysResource;
import com.chengxiaoxiao.model.web.pojos.SysRole;
import com.chengxiaoxiao.model.web.pojos.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/15 10:05 下午
 * @Description:
 */
public interface SysRoleService extends BaseService<SysRole, String> {
    /**
     * 条件搜索
     *
     * @param search
     * @param pageRequest
     * @return
     */
    Page search(SysRoleSearchDto search, PageRequest pageRequest);

    /**
     * 增加角色
     *
     * @param sysRole
     * @return
     */
    SysRole insert(SysRoleModelDto sysRole);

    /**
     * 更新角色信息
     *
     * @param id
     * @param sysRole
     * @return
     */
    SysRole update(String id, SysRoleModelDto sysRole);

    /**
     * 根据用户Id获取角色列表
     *
     * @param id 用户Id
     * @return
     */
    List<SysRoleSimpleDtos> getRolesByUserId(String id);

    /**
     * 根据父Id获取角色树
     *
     * @param parentId
     * @return
     */
    SysRoleTreeDto treeRolesByParentId(String parentId);

    /**
     * 根据角色Id查询其拥有的用户
     *
     * @param roleId
     * @return
     */
    List<SysUser> findUsersByRoleId(String roleId);

    /**
     * 查询角色下所拥有的的资源信息
     *
     * @param roleId
     * @return
     */
    List<SysResource> findResourcesByRoleId(String roleId);

    /**
     * 给角色分配资源
     *
     * @param roleId
     * @param resourceIds
     */
    void dispatchResourceByRoleId(String roleId, String[] resourceIds);
}
