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
    @ApiOperation("删除角色信息")
    @ApiImplicitParam(name = "id", value = "角色ID", dataType = "string", required = true, paramType = "path")
    Result delete(String id);

    /**
     * 根据角色Id查询角色下的用户信息
     *
     * @param roleId 角色Id
     * @return
     */
    @ApiOperation("查询某角色的用户列表")
    Result<List<SysUser>> getUsersByRoleId(@ApiParam(name = "roleId", value = "角色Id", required = true) String roleId);

    /**
     * 根据父Id获取树形角色树
     *
     * @param parentId 父ID
     * @return
     */
    @ApiOperation("根据父Id获取树形角色树")
    Result<SysRoleTreeDto> treeRoleByParent(@ApiParam(name = "parentId", value = "角色父Id") String parentId);

    /**
     * 给角色分配资源
     *
     * @param roleId      角色Id
     * @param resourceIds 资源Id数组
     * @return
     */
    @ApiOperation("给角色分配资源")
    Result dispatchResourceByRoleId(@ApiParam(name = "roleId", value = "角色Id", required = true) String roleId, @ApiParam(name = "resourceIds", value = "资源Id数组", required = true, type = "array") String[] resourceIds);

    /**
     * 为角色分配用户
     *
     * @param roleId  角色Id
     * @param userIds 用户Id数组
     * @return
     */
    @ApiOperation("为角色分配用户")
    Result dispatchUserByRoleId(@ApiParam(name = "roleId", value = "角色Id", required = true) String roleId, @ApiParam(name = "userIds", value = "资源Id数组", required = true, type = "array") String[] userIds);

    /**
     * 获取角色下的资源列表
     *
     * @param roleId 角色Id
     * @return
     */
    @ApiOperation("获取角色下的资源列表")
    Result<List<SysResource>> getResourcesByRoleId(@ApiParam(name = "roleId", value = "角色Id", required = true) String roleId);

}
