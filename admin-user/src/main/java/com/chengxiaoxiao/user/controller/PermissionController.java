package com.chengxiaoxiao.user.controller;


import com.chengxiaoxiao.model.common.dtos.result.Result;
import com.chengxiaoxiao.model.user.entity.Permission;
import com.chengxiaoxiao.user.service.PermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权限 菜单管理
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //获取全部菜单
    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public Result indexAllPermission() {
        List<Permission> list = permissionService.queryAllMenuGuli();
        Map<String, List> hash = new HashMap<>();
        hash.put("children", list);
        return Result.success(hash);
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        permissionService.removeChildByIdGuli(id);
        return Result.success(true);
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public Result doAssign(String roleId, String[] permissionId) {
        permissionService.saveRolePermissionRealtionShipGuli(roleId, permissionId);
        return Result.success(true);
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public Result toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        Map<String, List> hash = new HashMap<>();
        hash.put("children", list);
        return Result.success(hash);
    }


    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public Result save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return Result.success(true);
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public Result updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return Result.success(true);
    }

}

