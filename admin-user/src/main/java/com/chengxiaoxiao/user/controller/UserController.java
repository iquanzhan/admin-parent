package com.chengxiaoxiao.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengxiaoxiao.model.common.dtos.result.PageResult;
import com.chengxiaoxiao.model.common.dtos.result.Result;
import com.chengxiaoxiao.model.user.entity.User;
import com.chengxiaoxiao.user.service.RoleService;
import com.chengxiaoxiao.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/user")
//@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取管理用户分页列表")
    @GetMapping("{page}/{limit}")
    public Result<PageResult<User>> index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    User userQueryVo) {
        Page<User> pageParam = new Page<>(page, limit);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(userQueryVo.getUsername())) {
            wrapper.like("username", userQueryVo.getUsername());
        }
        userService.page(pageParam, wrapper);

        PageResult<User> pageResult = new PageResult<>();
        pageResult.setPageNumber(page);
        pageResult.setTotal(pageParam.getTotal());
        pageResult.setRows(pageParam.getRecords());

        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    @ApiParam(name = "id", required = true, type = "path")
    public Result<User> detail(@PathVariable String id) {
        User user = userService.getById(id);
        return Result.success(user);
    }


    @ApiOperation(value = "新增管理用户")
    @PostMapping
    public Result<User> save(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.save(user);
        return Result.success(user);
    }

    @ApiOperation(value = "修改管理用户")
    @PutMapping("/{id}")
    @ApiParam(name = "id", value = "用户ID", required = true)
    public Result<User> updateById(@PathVariable String id, @RequestBody User user) {
        user.setId(id);
        userService.updateById(user);
        return Result.success(user);
    }

    @ApiOperation(value = "删除管理用户")
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable String id) {
        userService.removeById(id);
        return Result.success(true);
    }

    @ApiOperation(value = "根据id列表删除管理用户")
    @DeleteMapping("batchRemove")
    public Result<Boolean> batchRemove(@RequestBody List<String> idList) {
        userService.removeByIds(idList);
        return Result.success(true);
    }

    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Result<Map<String, Object>> toAssign(@PathVariable String userId) {
        Map<String, Object> roleMap = roleService.findRoleByUserId(userId);
        return Result.success(roleMap);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public Result<Boolean> doAssign(@RequestParam String userId, @RequestParam String[] roleId) {
        roleService.saveUserRoleRealtionShip(userId, roleId);
        return Result.success(true);
    }
}

