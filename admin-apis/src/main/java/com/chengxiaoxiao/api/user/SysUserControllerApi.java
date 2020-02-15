package com.chengxiaoxiao.api.user;

import com.chengxiaoxiao.model.common.dtos.query.PageQueryDtos;
import com.chengxiaoxiao.model.common.dtos.result.Result;
import com.chengxiaoxiao.model.web.dtos.SysUserModelDto;
import com.chengxiaoxiao.model.web.dtos.SysUserSearchDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 用户接口类
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 11:21 下午
 * @Description:
 */
@Api(value = "用户管理接口", description = "用户管理接口，提供用户的增、删、改、查")
public interface SysUserControllerApi {
    @ApiOperation("根据Id查询用户信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "path")
    Result findById(String id);

    @ApiOperation("条件查询用户信息")
    Result search(SysUserSearchDto sysUserSearchDto, PageQueryDtos pageQueryDtos);

    @ApiOperation("添加用户")
    Result insert(SysUserModelDto user);

    @ApiOperation("更新用户信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "path")
    Result update(String id, SysUserModelDto user);

    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "path")
    Result delete(String id);
}
