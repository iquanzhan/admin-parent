package com.chengxiaoxiao.web.controller.v1;

import com.chengxiaoxiao.api.user.SysRoleControllerApi;
import com.chengxiaoxiao.model.common.dtos.query.PageQueryDtos;
import com.chengxiaoxiao.model.common.dtos.result.PageResult;
import com.chengxiaoxiao.model.common.dtos.result.Result;
import com.chengxiaoxiao.model.web.dtos.query.sysrole.SysRoleModelDto;
import com.chengxiaoxiao.model.web.dtos.query.sysrole.SysRoleSearchDto;
import com.chengxiaoxiao.model.web.dtos.result.SysRoleSimpleDtos;
import com.chengxiaoxiao.model.web.dtos.result.SysRoleTreeDto;
import com.chengxiaoxiao.model.web.pojos.SysResource;
import com.chengxiaoxiao.model.web.pojos.SysRole;
import com.chengxiaoxiao.model.web.pojos.SysRoleResource;
import com.chengxiaoxiao.model.web.pojos.SysUser;
import com.chengxiaoxiao.web.controller.BaseController;
import com.chengxiaoxiao.web.service.SysResourceService;
import com.chengxiaoxiao.web.service.SysRoleService;
import com.chengxiaoxiao.web.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息接口
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/15 10:24 下午
 * @Description:
 */
@RestController
@RequestMapping("/v1/sys-role")
public class SysRoleController extends BaseController implements SysRoleControllerApi {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysResourceService sysResourceService;


    @GetMapping("")
    @Override
    @PreAuthorize("hasPermission('/v1/sys-role','ADMIN')")
    public Result<PageResult<SysRole>> search(SysRoleSearchDto sysRoleSearchDto, PageQueryDtos dtos) {
        Page<SysRole> search = sysRoleService.search(sysRoleSearchDto, getPageRequest());
        return Result.success(new PageResult<>(search));
    }

    @GetMapping("/{id}")
    @Override
    public Result<SysRole> find(@PathVariable String id) {
        return Result.success(sysRoleService.find(id));
    }

    @PostMapping("")
    @Override
    public Result<SysRole> insert(@Valid @RequestBody SysRoleModelDto sysRole) {
        return Result.success(sysRoleService.insert(sysRole));
    }

    @PutMapping("/{id}")
    @Override
    public Result<SysRole> update(@PathVariable String id, @Valid @RequestBody SysRoleModelDto sysRole) {
        return Result.success(sysRoleService.update(id, sysRole));
    }

    @Override
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        sysRoleService.delete(id);
        return Result.success(null);
    }

    @Override
    @GetMapping("/user/{roleId}")
    public Result<List<SysUser>> getUsersByRoleId(@PathVariable("roleId") String roleId) {
        return Result.success(sysUserService.findUsersByRoleId(roleId));
    }

    @Override
    @GetMapping("/tree/{parentId}")
    public Result<SysRoleTreeDto> treeRoleByParent(@Valid @NotNull(message = "父Id不允许为Null") @PathVariable String parentId) {
        return Result.success(sysRoleService.treeRolesByParentId(parentId));
    }

    @Override
    @GetMapping("/resource/{roleId}")
    public Result<List<SysResource>> getResourcesByRoleId(@PathVariable String roleId) {
        return Result.success(sysResourceService.findResourcesByRoleId(roleId));
    }

    @Override
    @PostMapping("/resource/{roleId}")
    public Result dispatchResourceByRoleId(@PathVariable String roleId, @RequestBody String[] resourceIds) {
        sysResourceService.dispatchResourceByRoleId(roleId,resourceIds);
        return Result.success(null);
    }

    @Override
    @PostMapping("/user/{roleId}")
    public Result dispatchUserByRoleId(@PathVariable("roleId") String roleId, @RequestBody String[] userIds) {
        sysRoleService.dispatchUserByRoleId(roleId,userIds);
        return Result.success(null);
    }
}
