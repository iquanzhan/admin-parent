package com.chengxiaoxiao.web.controller.v1;

import com.chengxiaoxiao.api.user.SysUserControllerApi;
import com.chengxiaoxiao.model.common.dtos.query.PageQueryDtos;
import com.chengxiaoxiao.model.common.dtos.result.PageResult;
import com.chengxiaoxiao.model.common.dtos.result.Result;
import com.chengxiaoxiao.model.web.dtos.SysUserModelDto;
import com.chengxiaoxiao.model.web.dtos.SysUserSearchDto;
import com.chengxiaoxiao.model.web.pojos.SysUser;
import com.chengxiaoxiao.web.controller.BaseController;
import com.chengxiaoxiao.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @Override
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return Result.success(sysUserService.find(id));
    }

    @Override
    @GetMapping(value = {""})
    public Result search(SysUserSearchDto sysUserSearchDto, PageQueryDtos pageQueryDtos) {
        Page<SysUser> search = sysUserService.search(sysUserSearchDto, getPageRequest());

        return Result.success(new PageResult<SysUser>(search));
    }

    @Override
    @PostMapping("")
    public Result insert(@Valid @RequestBody SysUserModelDto user) {
        return Result.success(sysUserService.insert(user));
    }

    @Override
    @PutMapping("/{id}")
    public Result update(@PathVariable String id, @RequestBody SysUserModelDto user) {
        return Result.success(sysUserService.update(id, user));
    }

    @Override
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        sysUserService.delete(id);
        return Result.success(null);
    }
}
