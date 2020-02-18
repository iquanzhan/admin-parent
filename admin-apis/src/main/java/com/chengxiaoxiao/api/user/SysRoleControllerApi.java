package com.chengxiaoxiao.api.user;

import com.chengxiaoxiao.model.common.dtos.query.PageQueryDtos;
import com.chengxiaoxiao.model.common.dtos.result.PageResult;
import com.chengxiaoxiao.model.common.dtos.result.Result;
import com.chengxiaoxiao.model.web.dtos.query.sysrole.SysRoleModelDto;
import com.chengxiaoxiao.model.web.dtos.query.sysrole.SysRoleSearchDto;
import com.chengxiaoxiao.model.web.dtos.result.SysRoleSimpleDtos;
import com.chengxiaoxiao.model.web.dtos.result.SysRoleTreeDto;
import com.chengxiaoxiao.model.web.pojos.SysResource;
import com.chengxiaoxiao.model.web.pojos.SysRole;
import com.chengxiaoxiao.model.web.pojos.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/15 10:08 下午
 * @Description:
 */

@Api(value = "角色信息接口", description = "提供对角色信息的增删改查信息")
public interface SysRoleControllerApi {
    /**
     * 条件查询
     *
     * @param search
     * @param dtos
     * @return
     */
    @ApiOperation("条件查询")
    Result<PageResult<SysRole>> search(SysRoleSearchDto search, PageQueryDtos dtos);

    /**
     * 根据Id获取对象信息
     *
     * @param id
     * @return
     */
    @ApiOperation("根据角色Id查询信息")
    @ApiImplicitParam(name = "id", value = "角色Id", required = true, dataType = "String", paramType = "path")
    Result<SysRole> find(String id);

    /**
     * 添加
     *
     * @param sysRole
     * @return
     */
    @ApiOperation("添加角色信息")
    Result<SysRole> insert(@ApiParam(name = "角色对象", value = "传入json格式", required = true) SysRoleModelDto sysRole);

    /**
     * 根据Id更新信息
     *
     * @param id
     * @param sysRole
     * @return
     */
    @ApiOperation("修改角色信息")
    @ApiImplicitParam(name = "id", value = "角色ID", dataType = "string", required = true, paramType = "path")
    Result<SysRole> update(String id, @ApiParam(name = "角色对象", value = "传入json格式", required = true) SysRoleModelDto sysRole);

    /**
     * 根据Id删除角色信息
     *
     * @param id
     * @return
     */
    @ApiImplicitParam(name = "id", value = "角色ID", dataType = "string", required = true, paramType = "path")
    Result delete(String id);

    /**
     * 根据用户Id获取角色列表
     *
     * @param id 用户Id
     * @return
     */
    @ApiOperation("根据用户Id获取角色列表")
    Result<List<SysRoleSimpleDtos>> getRolesByUserId(@ApiParam(name = "id", value = "用Id", required = true) String id);

    /**
     * 根据父Id获取树形角色树
     *
     * @param parentId 父ID
     * @return
     */
    @ApiOperation("根据父Id获取树形角色树")
    Result<SysRoleTreeDto> treeRoleByParent(@ApiParam(name = "parentId", value = "角色父Id") String parentId);

    /**
     * 根据角色Id查询角色下的用户信息
     *
     * @param roleId 角色Id
     * @return
     */
    @ApiOperation("查询角色下的用户信息")
    Result<List<SysUser>> getUsersByRoleId(@ApiParam(name = "roleId", value = "角色Id", required = true) String roleId);

    /**
     * 给角色分配资源
     *
     * @param roleId      角色Id
     * @param resourceIds 资源Id数组
     * @return
     */
    @ApiOperation("给用户分配角色")
    Result dispatchResourceByRoleId(@ApiParam(name = "roleId", value = "角色Id", required = true) String roleId, @ApiParam(name = "resourceIds", value = "资源Id数组", required = true, type = "array") String[] resourceIds);

    /**
     * 获取角色下的资源列表
     *
     * @param roleId 角色Id
     * @return
     */
    @ApiOperation("获取角色下的资源列表")
    Result<List<SysResource>> getResourcesByRoleId(@ApiParam(name = "roleId", value = "角色Id", required = true) String roleId);

}
