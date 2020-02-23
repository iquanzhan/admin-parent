package com.chengxiaoxiao.web.controller.v1;

import com.chengxiaoxiao.api.user.SysUserControllerApi;
import com.chengxiaoxiao.common.config.JwtConfig;
import com.chengxiaoxiao.model.common.dtos.query.PageQueryDtos;
import com.chengxiaoxiao.model.common.dtos.result.PageResult;
import com.chengxiaoxiao.model.common.dtos.result.Result;
import com.chengxiaoxiao.model.web.dtos.query.sysuser.SysLoginModelDto;
import com.chengxiaoxiao.model.web.dtos.query.sysuser.SysUserModelDto;
import com.chengxiaoxiao.model.web.dtos.query.sysuser.SysUserSearchDto;
import com.chengxiaoxiao.model.web.dtos.result.SysRoleSimpleDtos;
import com.chengxiaoxiao.model.web.dtos.result.UserInfoRolesDto;
import com.chengxiaoxiao.model.web.pojos.SysUser;
import com.chengxiaoxiao.web.controller.BaseController;
import com.chengxiaoxiao.web.service.SysRoleService;
import com.chengxiaoxiao.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户相关请求接口
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 10:48 下午
 * @Description:
 */
@RestController
@RequestMapping("/v1/sys-user")
public class SysUserController extends BaseController implements SysUserControllerApi {
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysRoleService sysRoleService;
    @Autowired
    JwtConfig jwtConfig;

    @Override
    @GetMapping("/{id}")
    public Result<SysUser> findById(@PathVariable String id) {
        return Result.success(sysUserService.find(id));
    }

    @Override
    @GetMapping(value = {""})
    public Result<PageResult<SysUser>> search(SysUserSearchDto sysUserSearchDto, PageQueryDtos pageQueryDtos) {
        Page<SysUser> search = sysUserService.search(sysUserSearchDto, getPageRequest());

        return Result.success(new PageResult<SysUser>(search));
    }

    @Override
    @PostMapping("")
    public Result<SysUser> insert(@Valid @RequestBody SysUserModelDto user) {
        return Result.success(sysUserService.insert(user));
    }

    @Override
    @PutMapping("/{id}")
    public Result<SysUser> update(@PathVariable String id, @RequestBody SysUserModelDto user) {
        return Result.success(sysUserService.update(id, user));
    }

    @Override
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        sysUserService.delete(id);
        return Result.success(null);
    }

    @Override
    @GetMapping("/user/{id}")
    public Result<List<SysRoleSimpleDtos>> getRolesByUserId(@NotNull @PathVariable String id) {
        return Result.success(sysRoleService.getRolesByUserId(id));
    }

    @Override
    @PostMapping("/login")
    public Result login(@RequestBody @Valid SysLoginModelDto loginModelDto) {
        String token = sysUserService.login(loginModelDto);
        return Result.success(token);
    }

    @Override
    @DeleteMapping("/logout/{id}")
    public Result logout(@PathVariable("id") @NotNull(message = "用户Id不允许为空") String id) {
        return null;
    }

    @Override
    @PostMapping("/role/{userId}")
    public Result dispatchRoleByUserId(@PathVariable @NotNull(message = "用户ID不允许为空") String userId, @RequestBody @NotNull(message = "角色ID数组不能为空") String[] roldIds) {
        sysRoleService.dispatchRoleByUserId(userId, roldIds);
        return Result.success(null);
    }

    @Override
    @GetMapping("/info")
    public Result<UserInfoRolesDto> info() {
        return Result.success(sysUserService.loadUserInfoBytoken(request.getHeader(jwtConfig.getTokenHeader())));
    }


}
