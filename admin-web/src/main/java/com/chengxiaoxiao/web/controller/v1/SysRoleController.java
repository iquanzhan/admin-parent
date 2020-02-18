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
import com.chengxiaoxiao.web.service.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("")
    @Override
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
    @GetMapping("/user/{id}")
    public Result<List<SysRoleSimpleDtos>> getRolesByUserId(@NotNull @PathVariable String id) {
        return Result.success(sysRoleService.getRolesByUserId(id));
    }

    @Override
    @GetMapping("/tree/{parentId}")
    public Result<SysRoleTreeDto> treeRoleByParent(@Valid @NotNull(message = "父Id不允许为Null") @PathVariable String parentId) {
        return Result.success(sysRoleService.treeRolesByParentId(parentId));
    }

    @Override
    @PostMapping("/user/{userId}")
    public Result dispatchRoleByUserId(@PathVariable @NotNull(message = "用户ID不允许为空") String userId, @RequestBody @NotNull(message = "角色ID数组不能为空") String[] roldIds) {
        sysRoleService.dispatchRoleByUserId(userId,roldIds);
        return Result.success(null);
    }

}
