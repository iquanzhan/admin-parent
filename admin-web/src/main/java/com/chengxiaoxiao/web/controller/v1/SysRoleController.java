package com.chengxiaoxiao.web.controller.v1;

import com.chengxiaoxiao.api.user.SysRoleControllerApi;
import com.chengxiaoxiao.model.common.dtos.query.PageQueryDtos;
import com.chengxiaoxiao.model.common.dtos.result.PageResult;
import com.chengxiaoxiao.model.common.dtos.result.Result;
import com.chengxiaoxiao.model.web.dtos.SysRoleModelDto;
import com.chengxiaoxiao.model.web.dtos.SysRoleSearchDto;
import com.chengxiaoxiao.model.web.pojos.SysRole;
import com.chengxiaoxiao.model.web.pojos.SysUser;
import com.chengxiaoxiao.web.controller.BaseController;
import com.chengxiaoxiao.web.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
}
