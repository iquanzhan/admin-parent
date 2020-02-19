package com.chengxiaoxiao.api.user;

import com.chengxiaoxiao.model.common.dtos.query.PageQueryDtos;
import com.chengxiaoxiao.model.common.dtos.result.PageResult;
import com.chengxiaoxiao.model.common.dtos.result.Result;
import com.chengxiaoxiao.model.web.dtos.query.sysresource.SysResourceModelDto;
import com.chengxiaoxiao.model.web.dtos.query.sysresource.SysResourceSearchDto;
import com.chengxiaoxiao.model.web.dtos.result.SysResourceTreeDto;
import com.chengxiaoxiao.model.web.pojos.SysResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/15 10:08 下午
 * @Description:
 */

@Api(value = "系统资源信息接口", description = "提供对系统资源信息的增删改查信息")
public interface SysResourceControllerApi {
    /**
     * 条件查询
     *
     * @param search
     * @param dtos
     * @return
     */
    @ApiOperation("条件查询")
    Result<PageResult<SysResource>> search(SysResourceSearchDto search, PageQueryDtos dtos);

    /**
     * 根据Id获取对象信息
     *
     * @param id
     * @return
     */
    @ApiOperation("根据资源Id查询信息")
    @ApiImplicitParam(name = "id", value = "资源Id", required = true, dataType = "String", paramType = "path")
    Result<SysResource> find(String id);

    /**
     * 添加
     *
     * @param sysRole
     * @return
     */
    @ApiOperation("添加资源信息")
    Result<SysResource> insert(@ApiParam(name = "资源对象", value = "传入json格式", required = true) SysResourceModelDto sysRole);

    /**
     * 根据Id更新信息
     *
     * @param id
     * @param sysRole
     * @return
     */
    @ApiOperation("修改资源信息")
    @ApiImplicitParam(name = "id", value = "资源ID", dataType = "string", required = true, paramType = "path")
    Result<SysResource> update(String id, @ApiParam(name = "资源对象", value = "传入json格式", required = true) SysResourceModelDto sysRole);

    /**
     * 根据Id删除资源信息
     *
     * @param id
     * @return
     */
    @ApiImplicitParam(name = "id", value = "资源ID", dataType = "string", required = true, paramType = "path")
    Result delete(String id);

    /**
     * 根据parentId树形显示资源信息
     *
     * @param parentId 资源父Id
     * @return
     */
    @ApiOperation("树形展示资源信息")
    Result<SysResourceTreeDto> tree(String parentId);

}
