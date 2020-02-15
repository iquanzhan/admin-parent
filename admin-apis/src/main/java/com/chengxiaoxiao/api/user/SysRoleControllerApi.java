package com.chengxiaoxiao.api.user;

import com.chengxiaoxiao.model.common.dtos.query.PageQueryDtos;
import com.chengxiaoxiao.model.common.dtos.result.Result;
import com.chengxiaoxiao.model.web.dtos.SysRoleModelDto;
import com.chengxiaoxiao.model.web.dtos.SysRoleSearchDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    Result search(SysRoleSearchDto search, PageQueryDtos dtos);

    /**
     * 根据Id获取对象信息
     *
     * @param id
     * @return
     */
    @ApiOperation("根据角色Id查询信息")
    @ApiImplicitParam(name = "id", value = "角色Id", required = true, dataType = "String", paramType = "path")
    Result find(String id);

    /**
     * 添加
     *
     * @param sysRole
     * @return
     */
    @ApiOperation("添加角色信息")
    Result insert(SysRoleModelDto sysRole);

    /**
     * 根据Id更新信息
     *
     * @param id
     * @param sysRole
     * @return
     */
    @ApiOperation("修改角色信息")
    @ApiImplicitParam(name = "id", value = "角色ID", dataType = "string", required = true, paramType = "path")
    Result update(String id, SysRoleModelDto sysRole);

    /**
     * 根据Id删除角色信息
     *
     * @param id
     * @return
     */
    @ApiImplicitParam(name = "id", value = "角色ID", dataType = "string", required = true, paramType = "path")
    Result delete(String id);
}
